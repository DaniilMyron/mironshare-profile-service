package com.miron.profileservice.infrastructure.persistence;

import com.miron.profileservice.domain.entity.Account;
import com.miron.profileservice.domain.spi.AccountRepository;
import com.miron.profileservice.infrastructure.mappers.AccountEntityMapper;
import com.miron.profileservice.infrastructure.persistence.entity.AccountEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class AccountRepositoryAdapter implements AccountRepository<Account> {
    private final AccountJpaRepository jpa;
    private final AccountEntityMapper mapper;

    public AccountRepositoryAdapter(AccountJpaRepository jpa, AccountEntityMapper mapper) {
        this.jpa = jpa;
        this.mapper = mapper;
    }

    @Override
    public Account save(Account account) {
        AccountEntity saved = jpa.save(mapper.toEntity(account));
        return mapper.toDomain(saved);
    }

    @Override
    public Optional<Account> findById(UUID id) {
        return jpa.findById(id).map(mapper::toDomain);
    }

    @Override
    public Optional<Account> findByUsername(String username) {
        return jpa.findByUsername(username).map(mapper::toDomain);
    }

    @Override
    public Optional<Account> findByAccountName(String accountName) {
        return jpa.findByAccountName(accountName).map(mapper::toDomain);
    }

    @Override
    public List<Account> findAll() {
        return jpa.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    public void deleteById(UUID id) {
        jpa.deleteById(id);
    }
}
