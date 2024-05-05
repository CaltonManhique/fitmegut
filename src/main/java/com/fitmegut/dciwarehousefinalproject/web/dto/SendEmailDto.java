package com.fitmegut.dciwarehousefinalproject.web.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class SendEmailDto {

    @NotNull(message = "Required field")
    @Size(min = 1, message = "Required field")
    @Pattern(regexp = "^[\\w!#$%&’*+/=?`{|}~^-]+(?:\\.[\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$",
            message = "Invalid email.")
    private String email;

    public @NotNull(message = "Required field") @Size(min = 1, message = "Required field") @Pattern(regexp = "^[\\w!#$%&’*+/=?`{|}~^-]+(?:\\.[\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$",
            message = "Invalid email.") String getEmail() {
        return email;
    }

    public void setEmail(@NotNull(message = "Required field") @Size(min = 1, message = "Required field") @Pattern(regexp = "^[\\w!#$%&’*+/=?`{|}~^-]+(?:\\.[\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$",
            message = "Invalid email.") String email) {
        this.email = email;
    }
}
