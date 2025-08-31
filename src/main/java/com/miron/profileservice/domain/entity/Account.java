package com.miron.profileservice.domain.entity;

import com.miron.profileservice.domain.spi.BCryptEncoderForAccountPassword;
import com.miron.profileservice.domain.valueObjects.AccountId;
import com.miron.profileservice.domain.valueObjects.AccountName;
import com.miron.profileservice.domain.valueObjects.AccountPassword;
import com.miron.profileservice.domain.valueObjects.AccountUsername;
import lombok.Setter;

import java.util.*;

@Setter
public class Account {
    private AccountId id;
    private AccountUsername username;
    private AccountPassword password;
    private AccountName accountName;
    private Set<Account> friends = new HashSet<>();
    private Set<Account> subscribers = new HashSet<>();
    private AdditionalInformation additionalInformation;

    public Account(String username, String password, String name, BCryptEncoderForAccountPassword encoder) {
        this.id = new AccountId();
        this.username = new AccountUsername(username);
        this.password = new AccountPassword(password, encoder);
        this.accountName = new AccountName(name);
    }

    public Account(AccountId id,
                   String username,
                   String password,
                   String accountName,
                   Set<Account> friends,
                   Set<Account> subscribers,
                   AdditionalInformation additionalInformation,
                   BCryptEncoderForAccountPassword encoder) {
        this.id = id;
        this.username = new AccountUsername(username);
        this.password = new AccountPassword(password, encoder);
        this.accountName = new AccountName(accountName);
        this.friends = friends;
        this.subscribers = subscribers;
        this.additionalInformation = additionalInformation;
    }

    public Account() {}

    public Account subscribeOnUser(Account user) {
        if (this.friends.contains(user)) {
            throw new IllegalArgumentException("Friend already exists");
        }
        if(!this.subscribers.contains(user)) {
            user.subscribers.add(this);
            return this;
        }
        this.friends.add(user);
        user.friends.add(this);
        this.subscribers.remove(user);
        return this;
    }

    public Account unsubscribeFromUser(Account user) {
        if(this.friends.contains(user)) {
            this.friends.remove(user);
            this.subscribers.add(user);
            user.friends.remove(this);
            return this;
        }
        else if(user.subscribers.contains(this)) {
            user.subscribers.remove(this);
            return this;
        }
        throw new IllegalArgumentException("Friend does not exist");
    }

    public Account changeAccountName(String name) {
        this.accountName = new AccountName(name);
        return this;
    }

    public Account changeAccountPassword(String oldPassword, String newPassword, BCryptEncoderForAccountPassword encoder) {
        var oldAccountPassword = new AccountPassword(oldPassword, encoder);
        var newAccountPassword = new AccountPassword(newPassword, encoder);
        if(!oldAccountPassword.getValue().equals(this.password.getValue())) {
            throw new IllegalArgumentException("Passwords do not match");
        }
        this.password = newAccountPassword;
        return this;
    }

    public UUID getId() {
        return id.getValue();
    }

    public String getUsername() {
        return username.getValue();
    }

    public String getPassword() {
        return password.getValue();
    }

    public String getAccountName() {
        return accountName.getValue();
    }

    public Set<Account> getAccountFriends() {
        return friends;
    }

    public Set<Account> getAccountSubscribers() {
        return subscribers;
    }

    public AdditionalInformation getAdditionalInformation() {
        if(additionalInformation == null) {
            return null;
        }
        return additionalInformation;
    }

    public Account setAdditionalInformation(AdditionalInformation additionalInformation) {
        this.additionalInformation = additionalInformation;
        return this;
    }
}
