package com.miron.profileservice.domain.spi;


import com.miron.profileservice.domain.entity.AdditionalInformation;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AdditionalInformationRepository {
    AdditionalInformation save(AdditionalInformation additionalInformation);
    Optional<AdditionalInformation> findById(UUID id);
    List<AdditionalInformation> findAll();
    void deleteById(UUID id);
}
