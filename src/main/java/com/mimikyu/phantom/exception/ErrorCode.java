package com.mimikyu.phantom.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    // Member
    MEMBER_NOT_FOUND(NOT_FOUND,"MEMBER-0001", "일치하는 회원 정보를 찾을 수 없습니다"),
    MEMBER_EMAIL_EXISTS(CONFLICT, "MEMBER-0002", "이미 존재하는 회원 이메일입니다"),
    MEMBER_NICKNAME_EXISTS(CONFLICT, "MEMBER-0003", "이미 존재하는 회원 닉네임입니다"),
    MEMBER_SIGNUP_FAIL(BAD_REQUEST, "MEMBER-0004", "가입 실패"),
    NOT_MASTER(BAD_REQUEST, "MEMBER-0005", "어드민이 아닙니다"),
    SELLER_NOT_FOUND(BAD_REQUEST, "MEMBER-001" , "존재하지 않는 회원입니다"),
    SELLER_EMAIL_EXISTS(CONFLICT, "MEMBER-002", "이미 존재하는 회원 이메일입니다"),
    WRONG_LOGIN(BAD_REQUEST, "LOGIN-001", "아이디 또는 비밀번호가 일치하지 않습니다."),
    JWT_ACCESS_TOKEN_EXPIRED(BAD_REQUEST, "TOKEN-0001", "Access Token Expired"),

    JWT_REFRESH_TOKEN_EXPIRED(BAD_REQUEST, "TOKEN-0002", "리프레시 토큰이 유효 기간이 지났습니다"),

    /* 401 UNAUTHORIZED : 인증되지 않은 사용자 */
    INVALID_AUTH_TOKEN(UNAUTHORIZED, "TOKEN-0003","권한 정보가 없는 토큰입니다"),
    INVALID_AUTH_REFRESH_TOKEN(UNAUTHORIZED, "TOKEN-0004","권한 정보가 없는 리프레시 토큰입니다"),


    //Project
    PROJECT_NOT_FOUND(NOT_FOUND, "PROJECT-0001", "존재하지 않는 프로젝트입니다"),
    PROJECT_EXISTS(CONFLICT, "PROJECT-0002", "이미 존재하는 프로젝트입니다"),
    NOT_OWNER(BAD_REQUEST, "PROJECT-0003", "프로젝트의 owner만 프로젝트를 수정할 수 있습니다"),

    //Team
    TEAM_NOT_FOUND(NOT_FOUND, "TEAM-0001", "존재하지 않는 팀입니다"),
    TEAM_EXISTS(CONFLICT, "TEAM-0002", "이미 존재하는 팀입니다."),

    //Communication
    COMMUNICATION_ERROR(BAD_REQUEST, "COMM-0001", "Communication error"),

    /* 400 BAD_REQUEST : 잘못된 요청 */
//    INVALID_REFRESH_TOKEN(BAD_REQUEST, "REFRESH-TOKEN-001","리프레시 토큰이 유효하지 않습니다"),
    MISMATCH_REFRESH_TOKEN(BAD_REQUEST,"REFRESH-TOKEN-002", "리프레시 토큰의 유저 정보가 일치하지 않습니다"),
    CANNOT_FOLLOW_MYSELF(BAD_REQUEST, "REFRESH-TOKEN-003","자기 자신은 팔로우 할 수 없습니다"),

    // Login
    LOGIN_ERROR(BAD_REQUEST, "LOGIN-001" , "Invalid Credentials Supplied"),

    // Logout
    LOGOUT_ERROR(BAD_REQUEST, "LOGOUT-001", "잘못된 요청입니다"),

    WRONG_PASSWORD(BAD_REQUEST, "PASS-001", "비밀번호가 잘못되었습니다"),

    OWNER_CANT_LEAVE(BAD_REQUEST, "STUDY-001", "스터디장은 스터디를 탈퇴할 수 없습니다"),

    USER_NOT_IN_STUDY(BAD_REQUEST, "STUDY-002", "스터디에 참여하지 않는 회원입니다"),

    OWNER_AUTH(BAD_REQUEST, "STUDY-003", "스터디 방장만이 스터디를 수정할 수 있습니다."),

    STUDY_NOT_FOUND(BAD_REQUEST, "STUDY-004", "존재하지 않는 스터디입니다");



    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
}
