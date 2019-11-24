package com.hellofresh.challenge.data;

import lombok.Data;

@Data
public class User {

    public enum UserGender {
        MALE,
        FEMALE;
    }

    private UserGender gender;
    private String customerFirstName;
    private String customerLastName;
    private String password;
    private int dayOfBirth;
    private int monthOfBirth;
    private int yearOfBirth;
    private String company;
    private String address1;
    private String address2;
    private String city;
    private String state;
    private String postCode;
    private String additionalInfo;
    private String phone;
    private String mobilePhone;
    private String alias;
    private String email;
}
