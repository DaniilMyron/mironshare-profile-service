package com.miron.profileservice.infrastructure.persistence;

import com.miron.profileservice.infrastructure.persistence.entity.AdditionalInformationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AdditionalInformationJpaRepository extends JpaRepository<AdditionalInformationEntity, UUID> {
}
