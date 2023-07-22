package com.mimikyu.phantom.controller;


import com.mimikyu.phantom.dto.ItemUploadRequest;
import com.mimikyu.phantom.dto.ItemUploadResponse;
import com.mimikyu.phantom.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping
@RestController
@RequiredArgsConstructor
public class ItemController {
    private final ItemService itemService;

    @PostMapping("/item/new")
    public ItemUploadResponse itemUploadProcess(@RequestBody ItemUploadRequest itemUploadRequest, @AuthenticationPrincipal UserDetails userDetails) throws Exception {
        return itemService.saveItem(itemUploadRequest);
    }


}
