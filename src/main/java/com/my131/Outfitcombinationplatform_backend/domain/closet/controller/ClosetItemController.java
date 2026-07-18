package com.my131.Outfitcombinationplatform_backend.domain.closet.controller;

import com.my131.Outfitcombinationplatform_backend.domain.closet.service.ClosetItemService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/closet/item")
public class ClosetItemController {
    private final ClosetItemService closetItemService;

    @PostMapping("/add/{clothingItemId}")
    public ResponseEntity<Void> addClosetItem(@PathVariable Long clothingItemId){
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/delete/{closetItemId}")
    public ResponseEntity<Void> deleteClosetItem(@PathVariable Long closetItemId){
        return ResponseEntity.noContent().build();
    }

}
