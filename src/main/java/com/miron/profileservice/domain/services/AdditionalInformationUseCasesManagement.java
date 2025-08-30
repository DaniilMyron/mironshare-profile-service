package com.miron.profileservice.domain.services;

import com.miron.profileservice.domain.api.AdditionalInformationService;
import com.miron.profileservice.domain.entity.AdditionalInformation;
import com.miron.profileservice.domain.usecases.CreateAdditionalInformation;

public class AdditionalInformationUseCasesManagement implements AdditionalInformationService {
    private final CreateAdditionalInformation createAdditionalInformationUseCase;

    public AdditionalInformationUseCasesManagement(CreateAdditionalInformation createAdditionalInformationUseCase) {
        this.createAdditionalInformationUseCase = createAdditionalInformationUseCase;
    }

    @Override
    public AdditionalInformation createAdditionalInformation(String username, String picture, Integer age, String gender, String about) {
        return createAdditionalInformationUseCase.execute(username, picture, age, gender, about);
    }
}
