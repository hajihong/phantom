package com.mimikyu.phantom.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
public class LoginRequest {

    @NotBlank
    @Email
    private String email;

    @NotNull
    private String password;

    public LoginRequest(final String email, final String password) {
        this.email = email;
        this.password = password;
    }
}
