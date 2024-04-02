package com.fitmegut.dciwarehousefinalproject.web.dto;

import jakarta.persistence.Column;

public class MemberRegistrationDto {

    private String firstName;

    private String lastName;

    private String nickname;

    private String birthdate; // Date

    private String gender; // radio button

    private String email;

    private String phoneNumber;

    private String country;

    private String city;

    private String address;

    private String userType; // Private or company

    private String password;

    public MemberRegistrationDto(){}

    public MemberRegistrationDto(String firstName, String lastName, String nickname, String birthdate, String gender,
                                 String email, String phoneNumber, String country, String city, String address,
                                 String userType, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.nickname = nickname;
        this.birthdate = birthdate;
        this.gender = gender;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.country = country;
        this.city = city;
        this.address = address;
        this.userType = userType;
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
