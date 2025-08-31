package com.miron.profileservice.infrastructure.controller.dto;

import java.util.UUID;

public record LoginRequest(UUID id, String password) {
}
