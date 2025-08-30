package com.miron.profileservice.domain.usecases.impl;

import com.miron.profileservice.domain.spi.BCryptEncoderForAccountPassword;
import com.miron.profileservice.domain.springAnnotations.DomainUseCase;
import com.miron.profileservice.domain.usecases.CreateAccount;
import com.miron.profileservice.domain.entity.Account;
import com.miron.profileservice.domain.spi.AccountRepository;

@DomainUseCase
public class CreateAccountUseCase<T extends Account> implements CreateAccount<T> {
    private final AccountRepository<T> accountRepository;
    private final BCryptEncoderForAccountPassword encoder;

    public CreateAccountUseCase(AccountRepository<T> accountRepository, BCryptEncoderForAccountPassword encoder) {
        this.accountRepository = accountRepository;
        this.encoder = encoder;
    }

    @Override
    public T execute(String username, String password, String name) {
        if(accountRepository.findByUsername(username).isPresent()) {
            throw new IllegalArgumentException("Account with username " + username + " already exists");
        }
        var account = new Account(username, password, name, encoder);
        return accountRepository.save(account);
    }
}
