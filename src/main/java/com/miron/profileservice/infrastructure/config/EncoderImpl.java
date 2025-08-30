package com.miron.profileservice.infrastructure.config;

import com.miron.profileservice.domain.spi.BCryptEncoderForAccountPassword;
//TODO DELETE THIS
public class EncoderImpl implements BCryptEncoderForAccountPassword {
    @Override
    public String encode(String password) {
        return password + " encoded";
    }
}
