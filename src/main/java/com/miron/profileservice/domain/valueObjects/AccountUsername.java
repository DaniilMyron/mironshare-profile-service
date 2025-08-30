package com.miron.profileservice.domain.valueObjects;

public class AccountUsername implements ValueObject<String>{
    private String username;

    public AccountUsername(String username) {
        if (username.isEmpty() || username.isBlank()) {
            throw new IllegalArgumentException("Name cannot be empty or blank");
        } else if (username.length() > 20 || username.length() < 5) {
            throw new IllegalArgumentException("Name cannot exceed 20 characters and less than 5 characters");
        }
        this.username = username;
    }

    @Override
    public String getValue() {
        return this.username;
    }
}
