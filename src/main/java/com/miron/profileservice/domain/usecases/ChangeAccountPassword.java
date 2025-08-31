package com.miron.profileservice.domain.usecases;

import com.miron.profileservice.domain.entity.Account;

import java.util.UUID;

public interface ChangeAccountPassword<T extends Account> {
    T execute(UUID id, String oldPassword, String newPassword);
}
