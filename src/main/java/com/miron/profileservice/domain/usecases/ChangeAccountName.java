package com.miron.profileservice.domain.usecases;

import com.miron.profileservice.domain.entity.Account;


public interface ChangeAccountName<T> {
    T execute(String username, String accountName);
}
