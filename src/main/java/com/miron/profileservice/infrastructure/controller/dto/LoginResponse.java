package com.miron.profileservice.infrastructure.controller.dto;

public record LoginResponse(String token, int expires_in) {
}
