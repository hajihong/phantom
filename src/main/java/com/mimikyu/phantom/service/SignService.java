package com.mimikyu.phantom.service;

import com.mimikyu.phantom.common.CommonResult;
import com.mimikyu.phantom.dto.SellerSignUpDto;

public interface SignService {

    CommonResult join(SellerSignUpDto sellerSignUpDto);
}
