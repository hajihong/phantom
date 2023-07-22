package com.mimikyu.phantom.dto;

import com.mimikyu.phantom.domain.item.Item;
import com.mimikyu.phantom.domain.item.ItemSellStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ItemUploadRequest {

    private String itemName;
    private int price;
    private int stockNumber;

    public Item toItemEntity() {
        return Item.builder()
                .itemName(this.getItemName())
                .price((this.getPrice()))
                .stockNumber(this.getStockNumber())
                .itemSellStatus(ItemSellStatus.SELL)
                .build();
    }
}
