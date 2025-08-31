package com.miron.profileservice.infrastructure.mappers;

import com.miron.profileservice.domain.entity.Account;
import com.miron.profileservice.domain.spi.BCryptEncoderForAccountPassword;
import com.miron.profileservice.domain.valueObjects.AccountId;
import com.miron.profileservice.domain.valueObjects.AccountName;
import com.miron.profileservice.domain.valueObjects.AccountPassword;
import com.miron.profileservice.domain.valueObjects.AccountUsername;
import com.miron.profileservice.infrastructure.persistence.AccountJpaRepository;
import com.miron.profileservice.infrastructure.persistence.entity.AccountEntity;
import org.springframework.stereotype.Component;

import java.util.HashSet;

@Component
public class AccountEntityMapper {
    private final BCryptEncoderForAccountPassword  encoder;
    private final AccountJpaRepository repo;

    public AccountEntityMapper(BCryptEncoderForAccountPassword encoder, AccountJpaRepository repo) {
        this.encoder = encoder;
        this.repo = repo;
    }

    public Account toDomain(AccountEntity e) {
        var acc = new Account();

        acc.setId(new AccountId(e.getId()));
        acc.setUsername(new AccountUsername(e.getUsername()));
        acc.setPassword(new AccountPassword(e.getPassword(), encoder));
        acc.setAccountName(new AccountName(e.getAccountName()));

        var friendIds = e.getFriends().stream().map(AccountEntity::getId).toList();
        var subIds    = e.getSubscribers().stream().map(AccountEntity::getId).toList();

        if (!friendIds.isEmpty()) {
            e.setFriends(new HashSet<>(repo.findAllById(friendIds)));
        }
        if (!subIds.isEmpty()) {
            e.setSubscribers(new HashSet<>(repo.findAllById(subIds)));
        }
        return acc;
    }

    public AccountEntity toEntity(Account d) {
        var e = new AccountEntity();
        e.setId(d.getId());
        e.setUsername(d.getUsername());
        e.setPassword(d.getPassword());
        e.setAccountName(d.getAccountName());

        var friendIds = d.getAccountFriends().stream().map(Account::getId).toList();
        var subIds    = d.getAccountSubscribers().stream().map(Account::getId).toList();

        if (!friendIds.isEmpty()) {
            e.setFriends(new HashSet<>(repo.findAllById(friendIds)));
        }
        if (!subIds.isEmpty()) {
            e.setSubscribers(new HashSet<>(repo.findAllById(subIds)));
        }
        return e;
    }
}
