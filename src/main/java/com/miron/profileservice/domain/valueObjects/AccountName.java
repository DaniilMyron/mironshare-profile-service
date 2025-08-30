package com.miron.profileservice.domain.valueObjects;

public class AccountName implements ValueObject<String> {
    private String name;

    public AccountName(String name) {
        if (name.isEmpty() || name.isBlank()) {
            throw new IllegalArgumentException("Name cannot be empty or blank");
        } else if (name.length() > 15){
            throw new IllegalArgumentException("Name cannot exceed 15 characters");
        }
        this.name = name;
    }

    @Override
    public String getValue() {
        return this.name;
    }
}
