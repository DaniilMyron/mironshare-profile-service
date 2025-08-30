package com.miron.profileservice.domain.valueObjects;

public enum GenderInformation implements ValueObject<Enum<GenderInformation>> {
    MALE("MALE"), FEMALE("FEMALE"), UNDEFINED("UNDEFINED");

    private String gender;

    GenderInformation(String gender) {
        this.gender = gender;
    }

    @Override
    public GenderInformation getValue() {
        return this;
    }
}
