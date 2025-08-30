package com.miron.profileservice.domain.api;

import com.miron.profileservice.domain.entity.AdditionalInformation;

public interface AdditionalInformationService {
    AdditionalInformation createAdditionalInformation(String username, String picture, Integer age, String gender, String about);
}
