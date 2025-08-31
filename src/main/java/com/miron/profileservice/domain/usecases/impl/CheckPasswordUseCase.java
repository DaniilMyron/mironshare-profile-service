package com.miron.profileservice.domain.usecases.impl;

import com.miron.profileservice.domain.entity.Account;
import com.miron.profileservice.domain.spi.AccountRepository;
import com.miron.profileservice.domain.spi.BCryptEncoderForAccountPassword;
import com.miron.profileservice.domain.springAnnotations.DomainUseCase;
import com.miron.profileservice.domain.usecases.CheckPassword;

import java.util.UUID;

@DomainUseCase
public class CheckPasswordUseCase<T extends Account> implements CheckPassword {
    private final AccountRepository<T> accountRepository;
    private final BCryptEncoderForAccountPassword encoder;

    public CheckPasswordUseCase(AccountRepository<T> accountRepository, BCryptEncoderForAccountPassword encoder) {
        this.accountRepository = accountRepository;
        this.encoder = encoder;
    }

    @Override
    public boolean execute(UUID id, String password) {
        var account = accountRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        return account.getPassword().equals(password);
    }
}
