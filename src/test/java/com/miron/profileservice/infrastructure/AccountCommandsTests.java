package com.miron.profileservice.infrastructure;

import com.miron.profileservice.domain.api.AccountService;
import com.miron.profileservice.domain.entity.Account;
import com.miron.profileservice.domain.spi.AccountRepository;
import com.miron.profileservice.infrastructure.config.DomainBeansCreationConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;

import static org.assertj.core.api.Assertions.assertThat;

@Import(DomainBeansCreationConfig.class)
@WebMvcTest
public class AccountCommandsTests {
    @Autowired
    private AccountService<Account> accountService;
    @Autowired
    private AccountRepository<Account> accountRepository;
    private Account account;
    private final static String USERNAME = "USERNAME";
    private final static String PASSWORD = "PASSWORD1234";
    private final static String ACCOUNT_NAME = "danya";

    @BeforeEach
    public void setUp() {
        account = accountService.createAccount(USERNAME, PASSWORD, ACCOUNT_NAME);

    }

    @Test
    public void test_successful_create_account() {
        assertThat(account).isNotNull();
        assertThat(accountService.retrieveUser(account.getId())).isEqualTo(account);
    }
}
