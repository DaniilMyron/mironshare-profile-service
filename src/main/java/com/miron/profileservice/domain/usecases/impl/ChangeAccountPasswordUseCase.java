package com.miron.profileservice.domain.usecases.impl;

import com.miron.profileservice.domain.entity.Account;
import com.miron.profileservice.domain.spi.BCryptEncoderForAccountPassword;
import com.miron.profileservice.domain.spi.AccountRepository;
import com.miron.profileservice.domain.springAnnotations.DomainUseCase;
import com.miron.profileservice.domain.usecases.ChangeAccountPassword;

import java.util.UUID;

@DomainUseCase
public class ChangeAccountPasswordUseCase<T extends Account> implements ChangeAccountPassword<T> {
    private final AccountRepository<T> accountRepository;
    private final BCryptEncoderForAccountPassword encoder;

    public ChangeAccountPasswordUseCase(AccountRepository<T> accountRepository, BCryptEncoderForAccountPassword encoder) {
        this.accountRepository = accountRepository;
        this.encoder = encoder;
    }

    @Override
    public T execute(UUID id, String oldPassword, String newPassword) {
        var account = accountRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        return accountRepository.save(account.changeAccountPassword(oldPassword, newPassword, encoder));
    }
}
