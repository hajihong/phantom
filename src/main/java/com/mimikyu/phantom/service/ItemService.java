package com.mimikyu.phantom.service;

import com.mimikyu.phantom.dto.ItemUploadRequest;
import com.mimikyu.phantom.dto.ItemUploadResponse;

public interface ItemService {

    ItemUploadResponse saveItem(ItemUploadRequest itemUploadRequest);
}
