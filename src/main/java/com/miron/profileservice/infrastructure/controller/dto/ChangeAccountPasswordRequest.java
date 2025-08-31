package com.miron.profileservice.infrastructure.controller.dto;


public record ChangeAccountPasswordRequest(String oldPassword, String newPassword) {
}
