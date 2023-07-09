package com.mimikyu.phantom.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    SELLER_EMAIL_EXISTS(CONFLICT, "MEMBER-002", "이미 존재하는 회원 이메일입니다"),
    WRONG_LOGIN(BAD_REQUEST, "LOGIN-001", "아이디 또는 비밀번호가 일치하지 않습니다.");


    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
}
