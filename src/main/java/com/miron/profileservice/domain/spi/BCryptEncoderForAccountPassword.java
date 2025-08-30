package com.miron.profileservice.domain.spi;


public interface BCryptEncoderForAccountPassword {
    String encode(String password);
}
