package com.mimikyu.phantom.controller;

import com.mimikyu.phantom.common.CommonResult;
import com.mimikyu.phantom.dto.SellerSignUpDto;
import com.mimikyu.phantom.service.SignService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping
@RestController
@RequiredArgsConstructor
public class SignController {

    private final SignService signService;

    @PostMapping("/sign-up")
    public CommonResult signUp(@RequestBody SellerSignUpDto sellerSignUpDto) {
        return signService.join(sellerSignUpDto);
    }

}
