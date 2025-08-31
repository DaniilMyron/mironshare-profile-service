package com.miron.profileservice.infrastructure.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "spring.security.oauth2.resourceserver.jwt.keystore")
@Getter
@Setter
public class JwtKeyProperties {
    private String path;
    private String type = "PKCS12";
    private String password;
    private String keyAlias;
    private String keyPassword;
}
