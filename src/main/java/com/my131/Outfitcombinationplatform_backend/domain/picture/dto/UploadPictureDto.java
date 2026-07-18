package com.my131.Outfitcombinationplatform_backend.domain.picture.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.my131.Outfitcombinationplatform_backend.domain.clothing.dto.ClothingItemDto;
import com.my131.Outfitcombinationplatform_backend.domain.social.entity.Like;
import com.my131.Outfitcombinationplatform_backend.global.enums.PictureType;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Builder
@Data
public class UploadPictureDto {
    private String description;
    private String imageUrl;
    private PictureType pictureType;
    @Builder.Default
    private Set<String> styleTags = new HashSet<>();
    private ClothingItemDto clothingItem;
}
