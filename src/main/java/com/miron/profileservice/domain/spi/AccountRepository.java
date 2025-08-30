package com.miron.profileservice.domain.spi;

import com.miron.profileservice.domain.entity.Account;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AccountRepository<T extends Account> {
    T save(Account account);
    Optional<T> findById(UUID id);
    Optional<T> findByUsername(String username);
    Optional<T> findByAccountName(String accountName);
    List<T> findAll();
    void deleteById(UUID id);
}
