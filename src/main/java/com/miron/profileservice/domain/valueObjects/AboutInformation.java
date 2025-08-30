package com.miron.profileservice.domain.valueObjects;

public class AboutInformation implements ValueObject<String> {
    private String information;
    public AboutInformation(String information) {
        if(information != null) {
            if (information.isBlank() || information.isEmpty()) {
                throw new IllegalArgumentException("information is blank");
            } else if (information.length() > 255) {
                throw new IllegalArgumentException("information is too long");
            }
        }
        this.information = information;
    }

    @Override
    public String getValue() {
        return this.information;
    }
}
