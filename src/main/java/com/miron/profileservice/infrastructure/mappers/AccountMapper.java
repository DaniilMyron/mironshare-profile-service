package com.miron.profileservice.infrastructure.mappers;

import com.miron.profileservice.domain.entity.Account;
import com.miron.profileservice.infrastructure.controller.dto.AccountResponse;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AccountMapper {
    public AccountResponse toAccountResponse(Account account) {
        return new AccountResponse(account);
    }

    public List<AccountResponse> toAccountListResponse(List<? extends Account> accounts) {
        return accounts.stream()
                .map(AccountResponse::new)
                .toList();
    }
}
