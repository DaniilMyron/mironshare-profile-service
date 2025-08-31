package com.miron.profileservice.domain.usecases;

import com.miron.profileservice.domain.entity.AdditionalInformation;

import java.util.UUID;

public interface CreateAdditionalInformation {
    AdditionalInformation execute(UUID id, String picture, Integer age, String gender, String about);
}
