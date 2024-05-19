package com.webshop.dto;

import com.webshop.model.Account;

public class UpdateProfileAdvanceDto {
    private String currentPassword;
    private String newPassword;
    private String newUsername;
    private String newEmail;

    public UpdateProfileAdvanceDto() {}

    public UpdateProfileAdvanceDto(String currentPassword, String newPassword, String newUsername, String newEmail) {
        this.currentPassword = currentPassword;
        this.newPassword = newPassword;
        this.newUsername = newUsername;
        this.newEmail = newEmail;
    }

    public UpdateProfileAdvanceDto(Account account){
        this.currentPassword = account.getPassword();
        this.newPassword = account.getPassword();
        this.newUsername = account.getUsername();
        this.newEmail = account.getEmail();
    }

    public String getCurrentPassword() {
        return currentPassword;
    }

    public void setCurrentPassword(String currentPassword) {
        this.currentPassword = currentPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getNewUsername() {
        return newUsername;
    }

    public void setNewUsername(String newUsername) {
        this.newUsername = newUsername;
    }

    public String getNewEmail() {
        return newEmail;
    }

    public void setNewEmail(String newEmail) {
        this.newEmail = newEmail;
    }
}
