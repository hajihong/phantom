package com.mimikyu.phantom.dto;


import com.mimikyu.phantom.domain.user.Seller;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SellerSaveResponse {

    private Long id;

    private SellerSaveResponse(final Long id) {this.id = id;}

    public static SellerSaveResponse from(final Seller seller) {
        return new SellerSaveResponse(seller.getId());
    }

}
