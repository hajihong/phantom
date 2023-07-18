package com.mimikyu.phantom.service;

import com.mimikyu.phantom.common.CommonResult;
import com.mimikyu.phantom.common.ResponseService;
import com.mimikyu.phantom.domain.user.Seller;
import com.mimikyu.phantom.dto.*;
import com.mimikyu.phantom.exception.CustomException;
import com.mimikyu.phantom.exception.ErrorCode;
import com.mimikyu.phantom.repository.SellerRepository;
import com.mimikyu.phantom.security.jwt.JwtTokenProvider;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.Optional;
import java.util.concurrent.TimeUnit;


@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final SellerRepository sellerRepository;
    private final PasswordEncoder bCryptPasswordEncoder;

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

    private final ResponseService responseService;

    private final RedisTemplate<String, String> redisTemplate;


    @Value("${jwt.token.refresh-token-expire-length}")
    private long refresh_token_expire_time;


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
    public CommonResult validateDuplicateEmail(String email) {
        Optional<Seller> findUser = sellerRepository.findSellerByEmail(email);
        if (findUser.isPresent())
            throw new CustomException(ErrorCode.MEMBER_EMAIL_EXISTS);
        return responseService.getSuccessResult();
    }

    @Override
    @Transactional
    public ResponseEntity<?> login(LoginRequest loginRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

            String refresh_token = jwtTokenProvider.generateRefreshToken(authentication);
            SecurityContextHolder.getContext().setAuthentication(authentication);


            Seller userDetail = sellerRepository.findSellerByEmail(loginRequest.getEmail())
                    .orElseThrow(() -> new CustomException(ErrorCode.SELLER_NOT_FOUND));

            SellerDtos.SellerLogInRes sellerLogInRes = new SellerDtos.SellerLogInRes(userDetail);

            TokenDto tokenDto = new TokenDto(jwtTokenProvider.generateAccessToken(authentication), refresh_token);

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("user", sellerLogInRes);
            jsonObject.put("token", tokenDto);

            // Redis에 저장 - 만료 시간 설정을 통해 자동 삭제 처리
            redisTemplate.opsForValue().set(authentication.getName(), refresh_token, refresh_token_expire_time,
                    TimeUnit.MILLISECONDS);

            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add("Authorization", "Bearer " + tokenDto.getAccess_token());
            return new ResponseEntity<>(jsonObject, httpHeaders, HttpStatus.OK);
        } catch (AuthenticationException e) {
            e.printStackTrace();
            throw new CustomException(ErrorCode.LOGIN_ERROR);
        }
    }

    @Override
    public ResponseEntity<TokenDto> regenerateToken(RegenerateTokenDto refreshTokenDto) {
        String refresh_token = refreshTokenDto.getRefresh_token();
        try {
            // Refresh Token 검증
            if (!jwtTokenProvider.validateRefreshToken(refresh_token)) {
                throw new CustomException(ErrorCode.JWT_REFRESH_TOKEN_EXPIRED);
            }

            // Access Token 에서 User email를 가져온다.
            Authentication authentication = jwtTokenProvider.getAuthenticationByRefreshToken(refresh_token);

            // Redis에서 저장된 Refresh Token 값을 가져온다.
            String refreshToken = redisTemplate.opsForValue().get(authentication.getName());
            if(ObjectUtils.isEmpty(refreshToken)) {
                throw new CustomException(ErrorCode.LOGOUT_ERROR);
            }
            if(!refreshToken.equals(refresh_token)) {
                throw new CustomException(ErrorCode.MISMATCH_REFRESH_TOKEN);
            }

            // 토큰 재발행
            String new_refresh_token = jwtTokenProvider.generateRefreshToken(authentication);
            String new_access_token = jwtTokenProvider.generateAccessToken(authentication);
            TokenDto tokenDto = new TokenDto(
                    new_access_token,
                    new_refresh_token
            );

            // RefreshToken Redis에 업데이트
            redisTemplate.opsForValue().set(
                    authentication.getName(),
                    new_refresh_token,
                    refresh_token_expire_time,
                    TimeUnit.MILLISECONDS
            );

            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add("Authorization", "Bearer " + tokenDto.getAccess_token());

            return new ResponseEntity<>(tokenDto, httpHeaders, HttpStatus.OK);
        } catch (ExpiredJwtException e) {
            throw new CustomException(ErrorCode.JWT_REFRESH_TOKEN_EXPIRED);
        }
    }







}
