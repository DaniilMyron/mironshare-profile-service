package com.miron.profileservice.infrastructure.controller.dto;

import com.miron.profileservice.domain.entity.Account;


public class AccountResponse{
    private String accountUsername;
    private String accountName;
    private String accountPicture;
    private Integer userAge;
    private String userGender;
    private String userAbout;
    public AccountResponse(Account account) {
        this.accountUsername = account.getUsername();
        this.accountName = account.getAccountName();
        if(account.getAdditionalInformation() != null) {
            this.accountPicture = account.getAdditionalInformation().getAccountPicture();
            this.userAge = account.getAdditionalInformation().getAgeInformation();
            this.userGender = account.getAdditionalInformation().getGenderInformation().name();
            this.userAbout = account.getAdditionalInformation().getAboutInformation();
        }
    }

    public AccountResponse() {
    }

    public String getAccountUsername() {
        return accountUsername;
    }

    public String getAccountName() {
        return accountName;
    }

    public String getAccountPicture() {
        return accountPicture;
    }

    public Integer getUserAge() {
        return userAge;
    }

    public String getUserGender() {
        return userGender;
    }

    public String getUserAbout() {
        return userAbout;
    }
}
