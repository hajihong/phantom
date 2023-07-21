package com.mimikyu.phantom.security;

import com.mimikyu.phantom.domain.user.Seller;
import com.mimikyu.phantom.exception.CustomException;
import com.mimikyu.phantom.exception.ErrorCode;
import com.mimikyu.phantom.repository.SellerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private final SellerRepository sellerRepository;

    public Seller save(Seller user) {
        validateDuplicateMember(user);
        return sellerRepository.save(user);
    }

    public Seller findByEmail(String email) {
        return sellerRepository.findSellerByEmail(email).orElseThrow(() -> new CustomException(ErrorCode.MEMBER_NOT_FOUND));
    }

    private void validateDuplicateMember(Seller seller) {
        Optional<Seller> findUser = sellerRepository.findSellerByEmail(seller.getEmail());
        if (findUser.isPresent())
            throw new CustomException(ErrorCode.MEMBER_EMAIL_EXISTS);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws CustomException {

        Seller user = sellerRepository.findSellerByEmail(email)
                .orElseThrow(() -> new CustomException(ErrorCode.MEMBER_NOT_FOUND));

        return org.springframework.security.core.userdetails.User.builder().username(user.getEmail())
                .password(user.getPassword()).roles(user.getRole().toString()).build();

    }

}
