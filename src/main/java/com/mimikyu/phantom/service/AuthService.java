package com.mimikyu.phantom.service;


import com.mimikyu.phantom.dto.SellerSaveRequest;
import com.mimikyu.phantom.dto.SellerSaveResponse;

public interface AuthService {

    SellerSaveResponse join(SellerSaveRequest sellerSaveRequest);
}
