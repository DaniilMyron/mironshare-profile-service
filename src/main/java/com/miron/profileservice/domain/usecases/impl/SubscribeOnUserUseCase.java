package com.miron.profileservice.domain.usecases.impl;

import com.miron.profileservice.domain.entity.Account;
import com.miron.profileservice.domain.spi.AccountRepository;
import com.miron.profileservice.domain.springAnnotations.DomainUseCase;
import com.miron.profileservice.domain.usecases.SubscribeOnUser;

import java.util.UUID;

@DomainUseCase
public class SubscribeOnUserUseCase<T extends Account> implements SubscribeOnUser<T> {
    private final AccountRepository<T> accountRepository;

    public SubscribeOnUserUseCase(AccountRepository<T> accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public T execute(UUID id, UUID userIdToSubscribeOn) {
        var user = accountRepository.findById(id)
                .orElseThrow(IllegalArgumentException::new);

        var userToSubscribeOn = accountRepository.findById(userIdToSubscribeOn)
                .orElseThrow(IllegalArgumentException::new);

        if(userToSubscribeOn.getUsername().equals(user.getUsername())) {
            throw new IllegalArgumentException("Cannot subscribe to yourself");
        }

        user.subscribeOnUser(userToSubscribeOn);

        var userToSubscribeOnRepository = accountRepository.findById(userToSubscribeOn.getId())
                .orElseThrow(IllegalArgumentException::new);
        accountRepository.save(userToSubscribeOnRepository);
        return accountRepository.save(user);
    }
}
