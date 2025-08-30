package com.miron.profileservice.domain.valueObjects;

import java.util.UUID;

public class AccountId implements ValueObject<UUID> {
    private UUID uuid;

    public AccountId() {
        this.uuid = UUID.randomUUID();
    }

    @Override
    public UUID getValue() {
        return this.uuid;
    }
}
