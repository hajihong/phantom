package com.mimikyu.phantom.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public class BaseException extends RuntimeException {

    private final String message;
    private final HttpStatus httpStatus;

}