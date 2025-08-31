package com.miron.profileservice.domain.services;

import com.miron.profileservice.domain.api.AccountService;
import com.miron.profileservice.domain.springAnnotations.DomainService;
import com.miron.profileservice.domain.usecases.*;
import com.miron.profileservice.domain.entity.Account;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@DomainService
public class AccountUseCasesManagement<T extends Account> implements AccountService<T> {
    private final CreateAccount<T> createAccountUseCase;
    private final ChangeAccountName<T> changeAccountNameUseCase;
    private final ChangeAccountPassword<T> changeAccountPasswordUseCase;
    private final SubscribeOnUser<T> subscribeOnUserUseCase;
    private final RetrieveUser<T> retrieveAccountUseCase;

    public AccountUseCasesManagement(CreateAccount<T> createAccountUseCase,
                                     ChangeAccountName<T> changeAccountNameUseCase,
                                     ChangeAccountPassword<T> changeAccountPasswordUseCase,
                                     SubscribeOnUser<T> subscribeOnUserUseCase,
                                     RetrieveUser<T> retrieveAccountUseCase) {
        this.createAccountUseCase = createAccountUseCase;
        this.changeAccountNameUseCase = changeAccountNameUseCase;
        this.changeAccountPasswordUseCase = changeAccountPasswordUseCase;
        this.subscribeOnUserUseCase = subscribeOnUserUseCase;
        this.retrieveAccountUseCase = retrieveAccountUseCase;
    }

    @Override
    public T retrieveUser(UUID id) {
        return retrieveAccountUseCase.execute(id);
    }

    @Override
    public List<T> retrieveUsers(List<UUID> usersId) {
        List<T> accounts = new ArrayList<>();
        for (int i = 0; i < usersId.size(); i++) {
            accounts.add(retrieveAccountUseCase.execute(usersId.get(i)));
        }
        return accounts;
    }

    @Override
    public T createAccount(String username, String password, String name) {
        return createAccountUseCase.execute(username, password, name);
    }

    @Override
    public T changeNameById(UUID id, String accountName) {
        return changeAccountNameUseCase.execute(id, accountName);
    }

    @Override
    public T changePasswordById(UUID id, String oldPassword, String newPassword) {
        return changeAccountPasswordUseCase.execute(id, oldPassword, newPassword);
    }

    @Override
    public T subscribeOnUserById(UUID id, UUID userIdToSubscribeOn) {
        return subscribeOnUserUseCase.execute(id, userIdToSubscribeOn);
    }
}
