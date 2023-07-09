package com.mimikyu.phantom.dto;

import com.mimikyu.phantom.domain.user.Role;
import com.mimikyu.phantom.domain.user.Seller;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LoginResponse {

    private String name;
    private Role role;
    private String token;

    private LoginResponse(final String name, final Role role, final String token) {
        this.name = name;
        this.role = role;
        this.token = token;
    }

    public static LoginResponse from(final Seller seller, final String token) {
        return new LoginResponse(seller.getName(), seller.getRole(), token);
    }

}
