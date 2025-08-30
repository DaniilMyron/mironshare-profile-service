package com.miron.profileservice.domain.repository;

import com.miron.profileservice.domain.entity.Account;
import com.miron.profileservice.domain.spi.AccountRepository;
import com.miron.profileservice.domain.springAnnotations.DomainRepository;

import java.util.*;

@DomainRepository
public class AccountRepositoryInMemory implements AccountRepository<Account> {
    private final Map<UUID, Account> accounts = new HashMap<>();

    @Override
    public Account save(Account account) {
        accounts.computeIfPresent(account.getId(), (key, value) -> account);
        accounts.putIfAbsent(account.getId(), account);
        return accounts.get(account.getId());
    }

    @Override
    public Optional<Account> findById(UUID id) {
        return Optional.ofNullable(accounts.get(id));
    }

    @Override
    public Optional<Account> findByUsername(String username) {
        Account foundedAccount = null;
        for (Account account : accounts.values()) {
            if (account.getUsername().equals(username)) {
                foundedAccount = account;
                break;
            }
        }
        return Optional.ofNullable(foundedAccount);
    }

    @Override
    public Optional<Account> findByAccountName(String accountName) {
        Account foundedAccount = null;
        for (Account account : accounts.values()) {
            if (account.getAccountName().equals(accountName)) {
                foundedAccount = account;
                break;
            }
        }
        return Optional.ofNullable(foundedAccount);
    }

    @Override
    public List<Account> findAll() {
        return new ArrayList<>(accounts.values());
    }

    @Override
    public void deleteById(UUID id) {
        accounts.remove(id);
    }
}
