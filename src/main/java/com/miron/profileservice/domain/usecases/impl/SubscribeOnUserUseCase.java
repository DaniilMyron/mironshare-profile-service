package com.miron.profileservice.domain.usecases.impl;

import com.miron.profileservice.domain.entity.Account;
import com.miron.profileservice.domain.spi.AccountRepository;
import com.miron.profileservice.domain.springAnnotations.DomainUseCase;
import com.miron.profileservice.domain.usecases.SubscribeOnUser;

@DomainUseCase
public class SubscribeOnUserUseCase<T extends Account> implements SubscribeOnUser<T> {
    private final AccountRepository<T> accountRepository;

    public SubscribeOnUserUseCase(AccountRepository<T> accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public T execute(String username, Account userToSubscribeOn) {
        if(userToSubscribeOn.getUsername().equals(username)) {
            throw new IllegalArgumentException("Cannot subscribe to yourself");
        }
        var user = accountRepository.findByUsername(username)
                .orElseThrow(IllegalArgumentException::new)
                .subscribeOnUser(userToSubscribeOn);
        var userToSubscribeOnRepository = accountRepository.findById(userToSubscribeOn.getId())
                .orElseThrow(IllegalArgumentException::new);
        accountRepository.save(userToSubscribeOnRepository);
        return accountRepository.save(user);
    }
}
