package com.miron.profileservice.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "USER_ACCOUNT_TABLE")
public class AccountEntity{
    @Id
    private UUID id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(name = "account_name", nullable = false)
    private String accountName;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "account_friends",
            joinColumns = @JoinColumn(name = "account_id"),
            inverseJoinColumns = @JoinColumn(name = "friend_id")
    )
    private Set<AccountEntity> friends = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "account_subscribers",
            joinColumns = @JoinColumn(name = "account_id"),
            inverseJoinColumns = @JoinColumn(name = "subscriber_id")
    )
    private Set<AccountEntity> subscribers = new HashSet<>();

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "additional_information_id")
    private AdditionalInformationEntity additionalInformation;

    public AccountEntity() {
    }
}
