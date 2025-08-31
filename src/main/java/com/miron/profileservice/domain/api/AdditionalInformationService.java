package com.miron.profileservice.domain.api;

import com.miron.profileservice.domain.entity.AdditionalInformation;

import java.util.UUID;

public interface AdditionalInformationService {
    AdditionalInformation createAdditionalInformation(UUID id, String picture, Integer age, String gender, String about);
}
