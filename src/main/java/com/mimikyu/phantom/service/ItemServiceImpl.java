package com.mimikyu.phantom.service;


import com.mimikyu.phantom.domain.item.Item;
import com.mimikyu.phantom.dto.ItemUploadRequest;
import com.mimikyu.phantom.dto.ItemUploadResponse;
import com.mimikyu.phantom.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;


    @Override
    @Transactional
    public ItemUploadResponse saveItem(ItemUploadRequest itemUploadRequest) {
        Item newItem = itemUploadRequest.toItemEntity();
        Item item = itemRepository.save(newItem);
        return ItemUploadResponse.from(item);
    }
}
