package com.mimikyu.phantom.service;

import com.mimikyu.phantom.domain.user.Seller;
import com.mimikyu.phantom.dto.LoginRequest;
import com.mimikyu.phantom.dto.LoginResponse;
import com.mimikyu.phantom.dto.SellerSaveRequest;
import com.mimikyu.phantom.dto.SellerSaveResponse;
import com.mimikyu.phantom.exception.CustomException;
import com.mimikyu.phantom.exception.ErrorCode;
import com.mimikyu.phantom.repository.SellerRepository;
import com.mimikyu.phantom.security.TokenProvider;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class AuthServiceImpl implements AuthService {

    private final SellerRepository sellerRepository;
    private final PasswordEncoder bCryptPasswordEncoder;

    private final TokenProvider tokenProvider;

    public AuthServiceImpl(final SellerRepository sellerRepository,
                           final PasswordEncoder bCryptPasswordEncoder,
                           TokenProvider tokenProvider) {
        this.sellerRepository = sellerRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.tokenProvider = tokenProvider;
    }

    @Override
    @Transactional
    public SellerSaveResponse join(SellerSaveRequest sellerSaveRequest) {

        validateDuplicateEmail(sellerSaveRequest.getEmail());
        Seller newSeller = sellerSaveRequest.toSellerEntity();
        newSeller.hashPassword(bCryptPasswordEncoder);

        Seller seller = sellerRepository.save(newSeller);

        return SellerSaveResponse.from(seller);
    }

    @Override
    @Transactional
    public LoginResponse login(LoginRequest loginRequest) {
        Seller seller = sellerRepository.findSellerByEmail(loginRequest.getEmail())
                .filter(it -> bCryptPasswordEncoder.matches(loginRequest.getPassword(), it.getPassword()))
                .orElseThrow(() -> new CustomException(ErrorCode.WRONG_LOGIN));
        String token = tokenProvider.createToken(String.format("%s:%s", seller.getId(), seller.getRole()));
        return LoginResponse.from(seller, token);
    }


    @Transactional(readOnly = true)
    public void validateDuplicateEmail(final String email) {
        if(sellerRepository.existsByEmail(email)) {
            throw new CustomException(ErrorCode.SELLER_EMAIL_EXISTS);
        }
    }
}
