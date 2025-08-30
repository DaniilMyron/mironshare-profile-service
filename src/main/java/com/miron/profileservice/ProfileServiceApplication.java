package com.miron.profileservice;

import com.miron.profileservice.domain.entity.Account;
import com.miron.profileservice.domain.spi.AccountRepository;
import com.miron.profileservice.infrastructure.config.EncoderImpl;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProfileServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProfileServiceApplication.class, args);
    }
}
