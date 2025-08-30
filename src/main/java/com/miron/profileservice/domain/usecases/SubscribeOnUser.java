package com.miron.profileservice.domain.usecases;

import com.miron.profileservice.domain.entity.Account;


public interface SubscribeOnUser<T extends Account> {
    T execute(String username, Account userToSubscribeOn);
}
