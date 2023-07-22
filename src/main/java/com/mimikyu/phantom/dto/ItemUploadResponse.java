package com.mimikyu.phantom.dto;

import com.mimikyu.phantom.domain.item.Item;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ItemUploadResponse {

    private Long id;

    private String itemName;
    private int itemPrice;
    private int stockNumber;
    private ItemUploadResponse(final Long id, final String itemName, final int itemPrice, final int stockNumber) {
        this.id = id;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.stockNumber = stockNumber;
    }

    public static ItemUploadResponse from(final Item item) {
        return new ItemUploadResponse(item.getId(), item.getItemName(), item.getPrice(), item.getStockNumber());
    }


}
