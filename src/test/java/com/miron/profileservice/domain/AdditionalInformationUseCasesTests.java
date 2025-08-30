package com.miron.profileservice.domain;

import com.miron.profileservice.domain.entity.Account;
import com.miron.profileservice.domain.repository.AccountRepositoryInMemory;
import com.miron.profileservice.domain.repository.AdditionalInformationRepositoryInMemory;
import com.miron.profileservice.domain.spi.AccountRepository;
import com.miron.profileservice.domain.spi.AdditionalInformationRepository;
import com.miron.profileservice.domain.usecases.CreateAccount;
import com.miron.profileservice.domain.usecases.CreateAdditionalInformation;
import com.miron.profileservice.domain.usecases.impl.CreateAccountUseCase;
import com.miron.profileservice.domain.usecases.impl.CreateAdditionalInformationUseCase;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.assertj.core.api.Assertions.assertThat;

public class AdditionalInformationUseCasesTests {
    private final static String FIRST_USERNAME = "mironn1";
    private final static String SECOND_USERNAME = "mironn2";
    private AccountRepository<Account> accountRepository = new AccountRepositoryInMemory();
    private AdditionalInformationRepository additionalInformationRepository = new AdditionalInformationRepositoryInMemory();
    private CreateAdditionalInformation createAdditionalInformationUseCase = new CreateAdditionalInformationUseCase(additionalInformationRepository, accountRepository);
    private CreateAccount<Account> createAccountUseCase = new CreateAccountUseCase(accountRepository, null);

    @BeforeEach
    public void setup() {
        createAccountUseCase.execute(FIRST_USERNAME, "1234567890", "danya");
        createAccountUseCase.execute(SECOND_USERNAME, "1234567890", "danya");
    }

    @Test
    public void createAdditionalInformationUseCaseTest() {
        var account = accountRepository.findByUsername(FIRST_USERNAME).orElseThrow();
        assertThat(account.getAdditionalInformation()).isNull();
        createAdditionalInformationUseCase.execute(FIRST_USERNAME, "SDADASD", 10, "MALE", "Something about me");
        assertThat(account.getAdditionalInformation()).isNotNull();

        System.out.println(account.getAdditionalInformation().getAccountPicture());
        System.out.println(account.getAdditionalInformation().getAgeInformation());
        System.out.println(account.getAdditionalInformation().getGenderInformation());
        System.out.println(account.getAdditionalInformation().getAboutInformation());
    }
}
