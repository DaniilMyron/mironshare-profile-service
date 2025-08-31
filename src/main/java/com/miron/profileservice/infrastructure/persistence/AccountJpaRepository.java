package com.miron.profileservice.infrastructure.persistence;

import com.miron.profileservice.infrastructure.persistence.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface AccountJpaRepository extends JpaRepository<AccountEntity, UUID> {
    Optional<AccountEntity> findByUsername(String username);
    Optional<AccountEntity> findByAccountName(String accountName);
}
