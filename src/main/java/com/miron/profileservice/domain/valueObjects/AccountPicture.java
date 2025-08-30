package com.miron.profileservice.domain.valueObjects;

import java.util.Arrays;

public class AccountPicture implements ValueObject<String> {
    private byte[] profilePicture;

    public AccountPicture(byte[] profilePicture) {
        this.profilePicture = profilePicture;
    }

    @Override
    public String getValue() {
        return Arrays.toString(profilePicture);
    }
}
