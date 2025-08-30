package com.miron.profileservice.domain.usecases;

import com.miron.profileservice.domain.entity.AdditionalInformation;

public interface CreateAdditionalInformation {
    AdditionalInformation execute(String username, String picture, Integer age, String gender, String about);
}
