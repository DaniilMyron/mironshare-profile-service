package com.miron.profileservice.domain;

import com.miron.profileservice.domain.entity.Account;
import com.miron.profileservice.domain.spi.BCryptEncoderForAccountPassword;
import com.miron.profileservice.domain.repository.AccountRepositoryInMemory;
import com.miron.profileservice.domain.spi.AccountRepository;
import com.miron.profileservice.domain.usecases.ChangeAccountName;
import com.miron.profileservice.domain.usecases.ChangeAccountPassword;
import com.miron.profileservice.domain.usecases.RetrieveUser;
import com.miron.profileservice.domain.usecases.SubscribeOnUser;
import com.miron.profileservice.domain.usecases.impl.*;
import com.miron.profileservice.infrastructure.config.EncoderImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;


@Slf4j
public class AccountUseCasesTests {
    private final static String FIRST_USERNAME = "mironn1";
    private final static String SECOND_USERNAME = "mironn2";
    private BCryptEncoderForAccountPassword encoder = new EncoderImpl();
    private AccountRepository<Account> accountRepository = new AccountRepositoryInMemory();
    private CreateAccountUseCase createAccountUseCase = new CreateAccountUseCase(accountRepository, encoder);
    private ChangeAccountName changeAccountName = new ChangeAccountNameUseCase(accountRepository);
    private ChangeAccountPassword changeAccountPassword = new ChangeAccountPasswordUseCase(accountRepository, encoder);
    private SubscribeOnUser subscribeOnUser = new SubscribeOnUserUseCase(accountRepository);
    private RetrieveUser retrieveUser = new RetrieveUserUseCase(accountRepository);
    private Account firstUser;
    private Account secondUser;
    private UUID firstUserId;
    private UUID secondUserId;

    @BeforeEach
    public void setUp() {
        firstUser = createAccountUseCase.execute(FIRST_USERNAME, "1234567890", "danya");
        firstUserId = firstUser.getId();
        secondUser = createAccountUseCase.execute(SECOND_USERNAME, "1234567890", "danya");
        secondUserId = secondUser.getId();
    }

    @Test
    public void testCreateAccount() {
        assertThat(accountRepository.findAll().size()).isEqualTo(2);
    }

    @Test
    public void testRetrieveUser() {
        var user = retrieveUser.execute(firstUser.getId());
        assertThat(accountRepository.findAll().contains(user)).isTrue();
    }

    @Test
    public void testChangeAccountName() {
        changeAccountName.execute(firstUserId, "Not a danya");
        assertThat(accountRepository.findById(firstUserId).orElseThrow().getAccountName()).isEqualTo("Not a danya");
    }

    @Test
    public void testChangeAccountPassword() {
        var account = accountRepository.findById(firstUserId).orElseThrow();
        log.info(account.getPassword());
        changeAccountPassword.execute(firstUserId, "1234567890", "0987654321");
        assertThat(accountRepository.findById(firstUserId).orElseThrow().getPassword()).isEqualTo(encoder.encode("0987654321"));
    }

    @Test
    public void testSubscribeOnUser() {
        subscribeOnUser.execute(firstUserId, secondUserId);
        assertThat(accountRepository.findById(secondUserId).orElseThrow().getAccountSubscribers()).isNotEmpty();

        subscribeOnUser.execute(secondUserId, firstUserId);
        assertThat(accountRepository.findById(firstUserId).orElseThrow().getAccountFriends()).isNotEmpty();
        assertThat(accountRepository.findById(secondUserId).orElseThrow().getAccountFriends()).isNotEmpty();

        assertThat(accountRepository.findById(firstUserId).orElseThrow().getAccountSubscribers()).isEmpty();
        assertThat(accountRepository.findById(secondUserId).orElseThrow().getAccountSubscribers()).isEmpty();
    }
}
