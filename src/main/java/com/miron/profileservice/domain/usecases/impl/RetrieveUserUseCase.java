package com.miron.profileservice.domain.usecases.impl;

import com.miron.profileservice.domain.entity.Account;
import com.miron.profileservice.domain.spi.AccountRepository;
import com.miron.profileservice.domain.springAnnotations.DomainUseCase;
import com.miron.profileservice.domain.usecases.RetrieveUser;

import java.util.UUID;

@DomainUseCase
public class RetrieveUserUseCase<T extends Account> implements RetrieveUser<T> {
    private final AccountRepository<T> accountRepository;

    public RetrieveUserUseCase(AccountRepository<T> accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public T execute(UUID id) {
        return accountRepository.findById(id).orElse(null);
    }
}
