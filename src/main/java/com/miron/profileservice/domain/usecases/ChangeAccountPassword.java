package com.miron.profileservice.domain.usecases;

import com.miron.profileservice.domain.entity.Account;

public interface ChangeAccountPassword<T extends Account> {
    T execute(String username, String oldPassword, String newPassword);
}
