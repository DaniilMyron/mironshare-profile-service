package com.miron.profileservice.infrastructure.mappers;

import com.miron.profileservice.domain.entity.AdditionalInformation;
import com.miron.profileservice.domain.valueObjects.*;
import com.miron.profileservice.infrastructure.persistence.entity.AdditionalInformationEntity;
import org.springframework.stereotype.Component;

@Component
public class AdditionalInformationMapper {

    public AdditionalInformation toDomain(AdditionalInformationEntity e){
        var addInfo = new AdditionalInformation();
        addInfo.setAdditionalInformationId(new AdditionalInformationId(e.getId()));
        addInfo.setAccountPicture(new AccountPicture(e.getAccountPicture()));
        addInfo.setAgeInformation(new AgeInformation(e.getAgeInformation()));
        addInfo.setGenderInformation(GenderInformation.valueOf(e.getGenderInformation()));
        addInfo.setAboutInformation(new AboutInformation(e.getAboutInformation()));

        return addInfo;
    }

    public AdditionalInformationEntity toEntity(AdditionalInformation d){
        var addInfo = new AdditionalInformationEntity();

        addInfo.setId(d.getId());
        addInfo.setAccountPicture(d.getAccountPicture().getBytes());
        addInfo.setAgeInformation(d.getAgeInformation());
        addInfo.setGenderInformation(d.getGenderInformation().name());
        addInfo.setAboutInformation(d.getAboutInformation());
        return addInfo;
    }
}
