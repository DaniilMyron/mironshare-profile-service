package com.miron.profileservice.domain;

import com.miron.profileservice.domain.entity.Account;
import com.miron.profileservice.domain.spi.BCryptEncoderForAccountPassword;
import com.miron.profileservice.domain.repository.AccountRepositoryInMemory;
import com.miron.profileservice.domain.spi.AccountRepository;
import com.miron.profileservice.domain.usecases.ChangeAccountName;
import com.miron.profileservice.domain.usecases.ChangeAccountPassword;
import com.miron.profileservice.domain.usecases.SubscribeOnUser;
import com.miron.profileservice.domain.usecases.impl.ChangeAccountNameUseCase;
import com.miron.profileservice.domain.usecases.impl.ChangeAccountPasswordUseCase;
import com.miron.profileservice.domain.usecases.impl.CreateAccountUseCase;
import com.miron.profileservice.domain.usecases.impl.SubscribeOnUserUseCase;
import com.miron.profileservice.infrastructure.config.EncoderImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class AccountUseCasesTests {
    private final static String FIRST_USERNAME = "mironn1";
    private final static String SECOND_USERNAME = "mironn2";
    private BCryptEncoderForAccountPassword encoder = new EncoderImpl();
    private AccountRepository<Account> accountRepository = new AccountRepositoryInMemory();
    private CreateAccountUseCase createAccountUseCase = new CreateAccountUseCase(accountRepository, encoder);
    private ChangeAccountName changeAccountName = new ChangeAccountNameUseCase(accountRepository);
    private ChangeAccountPassword changeAccountPassword = new ChangeAccountPasswordUseCase(accountRepository, encoder);
    private SubscribeOnUser subscribeOnUser = new SubscribeOnUserUseCase(accountRepository);

    @BeforeEach
    public void setUp() {
        createAccountUseCase.execute(FIRST_USERNAME, "1234567890", "danya");
        createAccountUseCase.execute(SECOND_USERNAME, "1234567890", "danya");
    }

    @Test
    public void testCreateAccount() {
        assertThat(accountRepository.findAll().size()).isEqualTo(2);
    }

    @Test
    public void testChangeAccountName() {
        changeAccountName.execute(FIRST_USERNAME, "Not a danya");
        assertThat(accountRepository.findByUsername(FIRST_USERNAME).orElseThrow().getAccountName()).isEqualTo("Not a danya");
    }

    @Test
    public void testChangeAccountPassword() {
        changeAccountPassword.execute(FIRST_USERNAME, "1234567890", "0987654321");
        assertThat(accountRepository.findByUsername(FIRST_USERNAME).orElseThrow().getPassword()).isEqualTo("0987654321");
    }

    @Test
    public void testSubscribeOnUser() {
        subscribeOnUser.execute(FIRST_USERNAME, accountRepository.findByUsername(SECOND_USERNAME).orElseThrow());
        assertThat(accountRepository.findByUsername(SECOND_USERNAME).orElseThrow().getAccountSubscribers()).isNotEmpty();

        subscribeOnUser.execute(SECOND_USERNAME, accountRepository.findByUsername(FIRST_USERNAME).orElseThrow());
        assertThat(accountRepository.findByUsername(FIRST_USERNAME).orElseThrow().getAccountFriends()).isNotEmpty();
        assertThat(accountRepository.findByUsername(SECOND_USERNAME).orElseThrow().getAccountFriends()).isNotEmpty();

        assertThat(accountRepository.findByUsername(FIRST_USERNAME).orElseThrow().getAccountSubscribers()).isEmpty();
        assertThat(accountRepository.findByUsername(SECOND_USERNAME).orElseThrow().getAccountSubscribers()).isEmpty();
    }
}
