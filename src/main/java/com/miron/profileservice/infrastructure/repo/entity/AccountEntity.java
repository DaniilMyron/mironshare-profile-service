package com.miron.profileservice.infrastructure.repo.entity;

import com.miron.profileservice.domain.entity.Account;
import com.miron.profileservice.domain.spi.BCryptEncoderForAccountPassword;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "USER_ACCOUNT_TABLE")
public class AccountEntity extends Account {
    @Id
    private UUID id;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "AccountEntity_friends",
            joinColumns = @JoinColumn(name = "accountEntity_id"))
    private List<Friends> friends;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "AccountEntity_subscribers",
            joinColumns = @JoinColumn(name = "accountEntity_id"))
    private List<Subscribers> subscribers;

    public AccountEntity(String username, String password, String name, BCryptEncoderForAccountPassword encoder) {
        super(username, password, name, encoder);
    }

    public AccountEntity() {
    }
}
