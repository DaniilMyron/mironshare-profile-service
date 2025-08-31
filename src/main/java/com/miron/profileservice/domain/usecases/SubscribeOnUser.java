package com.miron.profileservice.domain.usecases;

import com.miron.profileservice.domain.entity.Account;

import java.util.UUID;


public interface SubscribeOnUser<T extends Account> {
    T execute(UUID id, UUID userIdToSubscribeOn);
}
