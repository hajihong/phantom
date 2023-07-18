package com.mimikyu.phantom.service;


import com.mimikyu.phantom.common.CommonResult;
import com.mimikyu.phantom.dto.*;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

public interface AuthService {

    SellerSaveResponse join(SellerSaveRequest sellerSaveRequest);

    @Transactional
    CommonResult validateDuplicateEmail(String email);

    ResponseEntity<?> login(LoginRequest loginRequest);

    ResponseEntity<TokenDto> regenerateToken(RegenerateTokenDto refreshTokenDto);
}
