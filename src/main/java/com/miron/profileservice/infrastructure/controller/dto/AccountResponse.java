package com.miron.profileservice.infrastructure.controller.dto;

import com.miron.profileservice.domain.entity.Account;
import lombok.Getter;


@Getter
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

}
