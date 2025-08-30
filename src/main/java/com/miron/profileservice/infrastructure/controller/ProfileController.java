package com.miron.profileservice.infrastructure.controller;

import com.miron.profileservice.domain.api.AccountService;
import com.miron.profileservice.domain.entity.Account;
import com.miron.profileservice.infrastructure.controller.dto.*;
import com.miron.profileservice.infrastructure.mappers.AccountMapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/profile")
public class ProfileController {
    private final AccountService<? extends Account> accountService;
    private final AccountMapper accountMapper;

    public ProfileController(AccountService<? extends Account> accountService, AccountMapper accountMapper) {
        this.accountService = accountService;
        this.accountMapper = accountMapper;
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountResponse> retrieveProfile(@PathVariable UUID id) {
        var account = accountService.retrieveUser(id);
        var response = accountMapper.toAccountResponse(account);
        return ResponseEntity.ok()
                .body(response);
    }

    @GetMapping("/retrieve-profiles")
    public ResponseEntity<List<AccountResponse>> retrieveProfiles(HttpEntity<String> httpEntity){
        var accounts = accountService.retrieveUsers(new AccountsRequest(httpEntity.getBody()).getUsersId());
        var response = accountMapper.toAccountListResponse(accounts);
        return ResponseEntity.ok()
                .body(response);
    }

    @PostMapping("/register")
    public ResponseEntity<AccountResponse> registrateUser(@RequestBody CreateAccountRequest createAccountRequest) {
        var account = accountService.createAccount(createAccountRequest.username(), createAccountRequest.password(), createAccountRequest.accountName());
        var response = accountMapper.toAccountResponse(account);
        return ResponseEntity.ok()
                .body(response);
    }

    @PutMapping("/change-account-name")
    public ResponseEntity<AccountResponse> changeAccountName(@RequestBody ChangeAccountNameRequest changeAccountNameRequest){
        var account = accountService.changeNameByUsername(changeAccountNameRequest.username(), changeAccountNameRequest.accountName());
        var response = accountMapper.toAccountResponse(account);
        return ResponseEntity.ok()
                .body(response);
    }

    @PutMapping("/change-account-password")
    public ResponseEntity<AccountResponse> changeAccountPassword(@RequestBody ChangeAccountPasswordRequest changeAccountPasswordRequest){
        var account = accountService.changePasswordByUsername(
                changeAccountPasswordRequest.username(),
                changeAccountPasswordRequest.oldPassword(),
                changeAccountPasswordRequest.newPassword()
        );
        var response = accountMapper.toAccountResponse(account);
        return ResponseEntity.ok()
                .body(response);
    }
}
