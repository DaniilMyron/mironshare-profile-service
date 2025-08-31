package com.miron.profileservice.domain.valueObjects;

import java.util.UUID;

public class AdditionalInformationId implements ValueObject<UUID> {
    private UUID id;
    public AdditionalInformationId() {
        this.id = UUID.randomUUID();
    }

    public AdditionalInformationId(UUID id) {
        this.id = id;
    }

    @Override
    public UUID getValue() {
        return this.id;
    }
}
