package com.miron.profileservice.domain.valueObjects;

public class AgeInformation implements ValueObject<Integer> {
    private Integer age;

    public AgeInformation(Integer age) {
        if(age != null) {
            if (age <= 0 || age > 99) {
                throw new IllegalArgumentException("Age must be between 0 and 99");
            }
        }
        this.age = age;
    }

    @Override
    public Integer getValue() {
        return this.age;
    }
}
