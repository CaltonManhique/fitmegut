package com.fitmegut.dciwarehousefinalproject.web.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class PasswordRecoverDto {

    @NotNull(message = "Required field")
    @Size(min = 1, message = "Required field")
    @Pattern(regexp = "^[\\w!#$%&’*+/=?`{|}~^-]+(?:\\.[\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$",
            message = "Invalid email.")
    private String email;

    private String oldPassword;

    @NotNull(message = "Required field")
    @Size(min = 8, message = "Password must contain at least 8 characters")
    @Pattern(regexp = ".*[A-Z].*.*[0-9].*",
            message = "Password must contain at least one digit, one lowercase and uppercase letter")
    private String newPassword;

    @NotNull(message = "Required field")
    @Size(min = 8, message = "Password must contain at least 8 characters")
    @Pattern(regexp = ".*[A-Z].*.*[0-9].*",
            message = "Password must contain at least one digit, one lowercase and uppercase letter")
    private String repeatPassword;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getRepeatPassword() {
        return repeatPassword;
    }

    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
    }
}
