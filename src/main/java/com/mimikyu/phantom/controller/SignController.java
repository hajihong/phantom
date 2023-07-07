package com.mimikyu.phantom.controller;

import com.mimikyu.phantom.common.CommonResult;
import com.mimikyu.phantom.dto.LoginRequest;
import com.mimikyu.phantom.dto.SellerSaveRequest;
import com.mimikyu.phantom.dto.SellerSaveResponse;
import com.mimikyu.phantom.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping
@RestController
@RequiredArgsConstructor
public class SignController {

    private final AuthService signService;

    @PostMapping("/sign-up")
    public SellerSaveResponse signUp(@RequestBody SellerSaveRequest sellerSaveRequest) {
        return signService.join(sellerSaveRequest);
    }

    @PostMapping("/sign-in")
    public ResponseEntity<?> signIn(@RequestBody LoginRequest loginRequest) {
        return signService.login(loginRequest);
    }

}
