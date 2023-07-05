package com.mimikyu.phantom.service;

import com.mimikyu.phantom.common.CommonResult;
import com.mimikyu.phantom.dto.SellerSignUpDto;
import com.mimikyu.phantom.repository.SellerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;



@Service
@RequiredArgsConstructor
public class SignServiceImpl implements SignService {

    private final SellerRepository sellerRepository;

    @Override
    public CommonResult join(SellerSignUpDto sellerSignUpDto) {
        return null;
    }
}
