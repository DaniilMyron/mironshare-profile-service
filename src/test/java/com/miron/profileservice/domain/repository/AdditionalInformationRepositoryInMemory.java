package com.miron.profileservice.domain.repository;

import com.miron.profileservice.domain.entity.AdditionalInformation;
import com.miron.profileservice.domain.spi.AdditionalInformationRepository;
import com.miron.profileservice.domain.springAnnotations.DomainRepository;

import java.util.*;

@DomainRepository
public class AdditionalInformationRepositoryInMemory implements AdditionalInformationRepository {
    private final Map<UUID, AdditionalInformation> additions = new HashMap<>();

    @Override
    public AdditionalInformation save(AdditionalInformation additionalInformation) {
        additions.computeIfPresent(additionalInformation.getId(), (key, value) -> additionalInformation);
        additions.putIfAbsent(additionalInformation.getId(), additionalInformation);
        return additions.get(additionalInformation.getId());
    }

    @Override
    public Optional<AdditionalInformation> findById(UUID id) {
        return Optional.ofNullable(additions.get(id));
    }

    @Override
    public List<AdditionalInformation> findAll() {
        return new ArrayList<>(additions.values());
    }

    @Override
    public void deleteById(UUID id) {
        additions.remove(id);
    }
}
