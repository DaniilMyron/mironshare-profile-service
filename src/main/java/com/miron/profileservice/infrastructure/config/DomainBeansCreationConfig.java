package com.miron.profileservice.infrastructure.config;

import com.miron.profileservice.domain.BasePackageClassesDefinerDomain;
import com.miron.profileservice.domain.spi.BCryptEncoderForAccountPassword;
import com.miron.profileservice.domain.springAnnotations.DomainRepository;
import com.miron.profileservice.domain.springAnnotations.DomainService;
import com.miron.profileservice.domain.springAnnotations.DomainUseCase;
import com.miron.profileservice.infrastructure.BasePackageClassesDefinerInfra;
import com.miron.profileservice.infrastructure.mappers.AccountMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        basePackageClasses = {BasePackageClassesDefinerDomain.class, BasePackageClassesDefinerInfra.class},
        includeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION, classes = {DomainService.class, DomainRepository.class, DomainUseCase.class})})
public class DomainBeansCreationConfig {
    @Bean
    public BCryptEncoderForAccountPassword encoder() {
        return new EncoderImpl();
    }
}
