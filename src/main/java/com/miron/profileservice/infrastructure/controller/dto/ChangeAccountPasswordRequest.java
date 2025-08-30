package com.miron.profileservice.infrastructure.controller.dto;

public record ChangeAccountPasswordRequest(String username, String oldPassword, String newPassword) {
}
