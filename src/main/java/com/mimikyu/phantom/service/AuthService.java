package com.mimikyu.phantom.service;


import com.mimikyu.phantom.dto.LoginRequest;
import com.mimikyu.phantom.dto.LoginResponse;
import com.mimikyu.phantom.dto.SellerSaveRequest;
import com.mimikyu.phantom.dto.SellerSaveResponse;
import org.springframework.http.ResponseEntity;

public interface AuthService {

    SellerSaveResponse join(SellerSaveRequest sellerSaveRequest);

    LoginResponse login(LoginRequest loginRequest);
}
