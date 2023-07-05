package com.mimikyu.phantom.domain.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {
    MASTER, ADMIN, USER
}
