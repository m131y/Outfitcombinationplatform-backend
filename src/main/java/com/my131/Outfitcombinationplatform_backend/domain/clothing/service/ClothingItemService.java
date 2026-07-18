package com.my131.Outfitcombinationplatform_backend.domain.clothing.service;

import com.my131.Outfitcombinationplatform_backend.domain.clothing.dto.ClothingItemDto;
import com.my131.Outfitcombinationplatform_backend.domain.clothing.entity.ClothingItem;
import com.my131.Outfitcombinationplatform_backend.domain.clothing.repository.ClothingItemRepository;
import com.my131.Outfitcombinationplatform_backend.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClothingItemService {
    private final ClothingItemRepository clothingItemRepository;

    // 수동 업로드 시 호출
    public ClothingItem createClothingItem(ClothingItemDto clothingItemDto, User user) {
        ClothingItem clothingItem = ClothingItem.of(
                clothingItemDto,
                clothingItemDto.getCategories(),
                user,
                clothingItemDto.getImageUrl()
        );

        return clothingItemRepository.save(clothingItem);
    }

    // 크롤링 시 호출
    public void crawlingClothingItem() {}

    // AI 추출 자동 업로드 시 호출
    public void extractAndSaveClothingItems() {

    }
}
