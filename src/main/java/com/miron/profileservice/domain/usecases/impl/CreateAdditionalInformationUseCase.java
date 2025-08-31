package com.miron.profileservice.domain.usecases.impl;

import com.miron.profileservice.domain.entity.Account;
import com.miron.profileservice.domain.entity.AdditionalInformation;
import com.miron.profileservice.domain.spi.AccountRepository;
import com.miron.profileservice.domain.spi.AdditionalInformationRepository;
import com.miron.profileservice.domain.springAnnotations.DomainUseCase;
import com.miron.profileservice.domain.usecases.CreateAdditionalInformation;

import java.util.UUID;

@DomainUseCase
public class CreateAdditionalInformationUseCase<T extends Account> implements CreateAdditionalInformation {
    private final AdditionalInformationRepository additionalInformationRepository;
    private final AccountRepository<T> accountRepository;

    public CreateAdditionalInformationUseCase(AdditionalInformationRepository additionalInformationRepository, AccountRepository<T> accountRepository) {
        this.additionalInformationRepository = additionalInformationRepository;
        this.accountRepository = accountRepository;
    }

    @Override
    public AdditionalInformation execute(UUID id, String picture, Integer age, String gender, String about) {
        var additionalInformation = AdditionalInformation.Builder()
                .setAccountPicture(picture)
                .setAge(age)
                .setGender(gender)
                .setAbout(about)
                .build();
        accountRepository.save(accountRepository.findById(id)
                .orElseThrow()
                .setAdditionalInformation(additionalInformation));
        return additionalInformationRepository.save(additionalInformation);
    }
}
