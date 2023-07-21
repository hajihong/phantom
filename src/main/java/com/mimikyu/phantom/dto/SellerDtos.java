package com.mimikyu.phantom.dto;


import com.mimikyu.phantom.domain.user.Role;
import com.mimikyu.phantom.domain.user.Seller;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Builder
public class SellerDtos {

    @Getter
    public static class SellerLogInRes {
        private Long id;
        private String email;
        private String name;
        private Role role;


        public SellerLogInRes(Seller seller) {
            this.id = seller.getId();
            this.email = seller.getEmail();
            this.name = seller.getName();
            this.role = seller.getRole();
        }
    }

        @Getter
        public static class TokensDto {
            private String access_token;
            private String refresh_token;

            public TokensDto(String access_token, String refresh_token) {
                this.access_token = access_token;
                this.refresh_token = refresh_token;
            }
        }
}
