package com.mimikyu.phantom.dto;

import com.mimikyu.phantom.domain.user.Role;
import com.mimikyu.phantom.domain.user.Seller;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SellerSignUpDto {

    @NotEmpty(message = "이메일은 필수 입력 값입니다.")
    @Email
    @ApiParam(value = "이메일", required = true)
    private String email;

    @NotEmpty(message = "비밀번호는 필수 입력 값입니다.")
    @ApiParam(value = "비밀번호", required = true)
    private String password;

    @NotBlank(message = "닉네임은 필수 입력 값입니다.")
    @ApiParam(value = "닉네임", required = true)
    private String name;

    public Seller toSellerEntity() {
        return Seller.builder()
                .email(this.getEmail())
                .password(this.getPassword())
                .name(this.getName())
                .role(Role.ADMIN)
                .build();
    }
}
