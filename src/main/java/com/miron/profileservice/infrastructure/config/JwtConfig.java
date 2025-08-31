package com.miron.profileservice.infrastructure.config;

import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ResourceLoader;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;

import java.io.InputStream;
import java.security.KeyStore;

@Configuration
@EnableConfigurationProperties(JwtKeyProperties.class)
public class JwtConfig {

    @Bean
    JwtEncoder jwtEncoder(JWKSource<SecurityContext> jwkSource) {
        return new NimbusJwtEncoder(jwkSource);
    }

    @Bean
    JWKSource<SecurityContext> jwkSource(JwtKeyProperties props, ResourceLoader resourceLoader) throws Exception {
        var resource = resourceLoader.getResource(props.getPath());

        KeyStore keyStore = KeyStore.getInstance(props.getType());
        try (InputStream is = resource.getInputStream()) {
            keyStore.load(is, props.getPassword().toCharArray());
        }

        RSAKey rsaKey = RSAKey.load(keyStore, props.getKeyAlias(), props.getKeyPassword().toCharArray());
        JWKSet jwkSet = new JWKSet(rsaKey);
        return (jwkSelector, securityContext) -> jwkSelector.select(jwkSet);
    }
}
