package com.webshop.dto;

import com.webshop.model.Account;

import java.time.LocalDate;

public class UpdateProfileBasicDto {
    private String name;
    private String lastName;
    private String phoneNumber;
    private LocalDate dateOfBirth;

    private String imagePath;

    private String description;

    public UpdateProfileBasicDto() {}

    public UpdateProfileBasicDto(String name, String lastName, String phoneNumber, LocalDate dateOfBirth, String imagePath, String description) {
        this.name = name;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.dateOfBirth = dateOfBirth;
        this.imagePath = imagePath;
        this.description = description;
    }

    public UpdateProfileBasicDto(Account account){
        this.name = account.getName();
        this.lastName = account.getLastName();
        this.phoneNumber = account.getPhoneNumber();
        this.dateOfBirth = account.getDateOfBirth();
        this.imagePath = account.getImagePath();
        this.description = account.getDescription();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
