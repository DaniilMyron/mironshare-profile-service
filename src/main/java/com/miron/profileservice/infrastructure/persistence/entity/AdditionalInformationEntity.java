package com.miron.profileservice.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "ADDITIONAL_INFORMATION")
@Getter @Setter
public class AdditionalInformationEntity {
    @Id
    private UUID id;
    private byte[] accountPicture;
    private int ageInformation;
    private String genderInformation;
    private String aboutInformation;
}

