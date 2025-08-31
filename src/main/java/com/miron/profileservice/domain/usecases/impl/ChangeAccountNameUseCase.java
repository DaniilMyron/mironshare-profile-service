package com.miron.profileservice.domain.usecases.impl;

import com.miron.profileservice.domain.entity.Account;
import com.miron.profileservice.domain.spi.AccountRepository;
import com.miron.profileservice.domain.springAnnotations.DomainUseCase;
import com.miron.profileservice.domain.usecases.ChangeAccountName;

import java.util.UUID;

@DomainUseCase
public class ChangeAccountNameUseCase<T extends Account> implements ChangeAccountName<T> {
    private final AccountRepository<T> accountRepository;

    public ChangeAccountNameUseCase(AccountRepository<T> accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public T execute(UUID id, String accountName) {
        var account = accountRepository.findById(id)
                .orElseThrow(IllegalArgumentException::new)
                .changeAccountName(accountName);
        return accountRepository.save(account);
    }
}
