package com.miron.profileservice.infrastructure.controller.dto;

public record CreateAccountRequest(String username, String password, String accountName) {
}
