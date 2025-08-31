package com.miron.profileservice.infrastructure.controller;

import com.miron.profileservice.domain.api.AccountService;
import com.miron.profileservice.domain.entity.Account;
import com.miron.profileservice.infrastructure.config.JwtTokenService;
import com.miron.profileservice.infrastructure.controller.dto.LoginRequest;
import com.miron.profileservice.infrastructure.controller.dto.LoginResponse;
import com.miron.profileservice.infrastructure.mappers.AccountEntityMapper;
import com.miron.profileservice.infrastructure.persistence.entity.AccountEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/profile")
public class AuthController {
    private final AccountService<? extends Account> accountService;
    private final JwtTokenService jwtTokenService;
    private final AccountEntityMapper accountEntityMapper;

    public AuthController(AccountService<? extends Account> accountService, JwtTokenService jwtTokenService, AccountEntityMapper accountEntityMapper) {
        this.accountService = accountService;
        this.jwtTokenService = jwtTokenService;
        this.accountEntityMapper = accountEntityMapper;
    }

    @PostMapping("/auth/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        AccountEntity accountEntity = accountService.checkPassword(request.id(), request.password())
                ? accountEntityMapper.toEntity(accountService.retrieveUser(request.id()))
                : null;
        if(accountEntity == null){
            throw new UsernameNotFoundException("Invalid username or password");
        }
        String token = jwtTokenService.generateToken(accountEntity);
        LoginResponse response = new  LoginResponse(token, 3600);
        return ResponseEntity.ok()
                .body(response);
    }
}
