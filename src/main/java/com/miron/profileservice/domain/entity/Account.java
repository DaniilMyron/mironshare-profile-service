package com.miron.profileservice.domain.entity;

import com.miron.profileservice.domain.spi.BCryptEncoderForAccountPassword;
import com.miron.profileservice.domain.valueObjects.AccountId;
import com.miron.profileservice.domain.valueObjects.AccountName;
import com.miron.profileservice.domain.valueObjects.AccountPassword;
import com.miron.profileservice.domain.valueObjects.AccountUsername;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Account {
    private AccountId id;
    private AccountUsername username;
    private AccountPassword password;
    private AccountName accountName;
    private List<Account> friends = new ArrayList<>();
    private List<Account> subscribers = new ArrayList<>();
    private AdditionalInformation additionalInformation;

    public Account(String username, String password, String name, BCryptEncoderForAccountPassword encoder) {
        this.id = new AccountId();
        this.username = new AccountUsername(username);
        this.password = new AccountPassword(password, encoder);
        this.accountName = new AccountName(name);
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

    public List<Account> getAccountFriends() {
        return friends;
    }

    public List<Account> getAccountSubscribers() {
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
