package com.mimikyu.phantom.service;

import com.mimikyu.phantom.domain.user.Seller;
import com.mimikyu.phantom.dto.SellerSaveRequest;
import com.mimikyu.phantom.dto.SellerSaveResponse;
import com.mimikyu.phantom.exception.CustomException;
import com.mimikyu.phantom.exception.ErrorCode;
import com.mimikyu.phantom.repository.SellerRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class AuthServiceImpl implements AuthService {

    private final SellerRepository sellerRepository;
    private final PasswordEncoder bCryptPasswordEncoder;

    public AuthServiceImpl(final SellerRepository sellerRepository,
                           final PasswordEncoder bCryptPasswordEncoder
    ) {
        this.sellerRepository = sellerRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
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

    @Transactional(readOnly = true)
    public void validateDuplicateEmail(final String email) {
        if(sellerRepository.existsByEmail(email)) {
            throw new CustomException(ErrorCode.SELLER_EMAIL_EXISTS);
        }
    }
}
