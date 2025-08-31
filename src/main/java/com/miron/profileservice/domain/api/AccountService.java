package com.miron.profileservice.domain.api;

import com.miron.profileservice.domain.entity.Account;

import java.util.List;
import java.util.UUID;


public interface AccountService<T extends Account> {
    T retrieveUser(UUID id);
    List<T> retrieveUsers(List<UUID> usersId);
    T createAccount(String username, String password, String name);
    T changeNameById(UUID id, String accountName);
    T changePasswordById(UUID id, String oldPassword, String newPassword);
    T subscribeOnUserById(UUID id, UUID usernameToSubscribeOn);
}
