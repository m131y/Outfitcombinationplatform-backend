package com.my131.Outfitcombinationplatform_backend.domain.clothing.dto;

import com.my131.Outfitcombinationplatform_backend.domain.clothing.entity.ClothingItemCategory;
import com.my131.Outfitcombinationplatform_backend.domain.user.dto.UserSummaryDto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@Builder
public class ClothingItemDto {
    private String name;
    private String imageUrl;
    private String brand;
    private String purchaseUrl;
    private UserSummaryDto userSummaryDto;
    private List<ClothingItemCategory> categories;
    private Set<String> styleTags;
}
