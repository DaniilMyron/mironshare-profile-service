package com.miron.profileservice.domain.usecases;

import com.miron.profileservice.domain.entity.Account;

public interface CreateAccount<T extends Account> {
    T execute(String username, String password, String name);
}
