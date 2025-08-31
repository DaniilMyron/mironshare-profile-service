package com.miron.profileservice.domain.entity;

import com.miron.profileservice.domain.valueObjects.*;
import lombok.Setter;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

@Setter
public class AdditionalInformation {
    private AdditionalInformationId additionalInformationId;
    private AccountPicture accountPicture = new AccountPicture(null);
    private AgeInformation ageInformation = new AgeInformation(null);
    private GenderInformation genderInformation = GenderInformation.UNDEFINED;
    private AboutInformation aboutInformation = new AboutInformation(null);

    public AdditionalInformation(){
        additionalInformationId = new AdditionalInformationId();
    }

    public UUID getId() {
        return additionalInformationId.getValue();
    }

    public String getAccountPicture() {
        return accountPicture.getValue();
    }

    public GenderInformation getGenderInformation() {
        return genderInformation.getValue();
    }

    public Integer getAgeInformation() {
        return ageInformation.getValue();
    }

    public String getAboutInformation() {
        return aboutInformation.getValue();
    }

    public AdditionalInformation changeAccountPicture(String picture) {
        this.accountPicture = new AccountPicture(picture.getBytes(StandardCharsets.UTF_8));
        return this;
    }

    public AdditionalInformation changeAgeInformation(int age) {
        this.ageInformation = new AgeInformation(age);
        return this;
    }

    public AdditionalInformation changeGenderInformation(String gender) {
        this.genderInformation = GenderInformation.valueOf(gender);
        return this;
    }

    public AdditionalInformation changeAboutInformation(String about) {
        this.aboutInformation = new AboutInformation(about);
        return this;
    }

    public static Builder Builder() {
        return new AdditionalInformation().new Builder();
    }

    public class Builder {

        private Builder() {}

        public Builder setAccountPicture(String accountPicture) {
            if(accountPicture == null) {
                return this;
            }
            AdditionalInformation.this.accountPicture = new AccountPicture(accountPicture.getBytes(StandardCharsets.UTF_8));
            return this;
        }

        public Builder setAge(Integer age) {
            if(age == null) {
                return this;
            }
            AdditionalInformation.this.ageInformation = new AgeInformation(age);
            return this;
        }

        public Builder setAbout(String about) {
            if(about == null) {
                return this;
            }
            AdditionalInformation.this.aboutInformation = new AboutInformation(about);
            return this;
        }

        public Builder setGender(String gender) {
            if(gender == null) {
                return this;
            }
            AdditionalInformation.this.genderInformation = GenderInformation.valueOf(gender);
            return this;
        }

        public AdditionalInformation build() {
            return AdditionalInformation.this;
        }

    }
}
