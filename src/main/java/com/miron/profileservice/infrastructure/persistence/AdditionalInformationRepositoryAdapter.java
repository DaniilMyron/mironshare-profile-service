package com.miron.profileservice.infrastructure.persistence;

import com.miron.profileservice.domain.entity.AdditionalInformation;
import com.miron.profileservice.domain.spi.AdditionalInformationRepository;
import com.miron.profileservice.infrastructure.mappers.AdditionalInformationMapper;
import com.miron.profileservice.infrastructure.persistence.entity.AdditionalInformationEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class AdditionalInformationRepositoryAdapter implements AdditionalInformationRepository {
    private final AdditionalInformationJpaRepository jpa;
    private final AdditionalInformationMapper mapper;

    public AdditionalInformationRepositoryAdapter(AdditionalInformationJpaRepository additionalInformationJpaRepository, AdditionalInformationMapper additionalInformationMapper) {
        this.jpa = additionalInformationJpaRepository;
        this.mapper = additionalInformationMapper;
    }

    @Override
    public AdditionalInformation save(AdditionalInformation additionalInformation) {
        AdditionalInformationEntity saved = jpa.save(mapper.toEntity(additionalInformation));
        return mapper.toDomain(saved);
    }

    @Override
    public Optional<AdditionalInformation> findById(UUID id) {
        return jpa.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<AdditionalInformation> findAll() {
        return jpa.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    public void deleteById(UUID id) {
        jpa.deleteById(id);
    }
}
