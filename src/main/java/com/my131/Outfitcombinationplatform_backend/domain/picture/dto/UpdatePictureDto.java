package com.my131.Outfitcombinationplatform_backend.domain.picture.dto;

import com.my131.Outfitcombinationplatform_backend.global.enums.PictureType;
import lombok.Builder;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Builder
@Data
public class UpdatePictureDto {
    private String description;
    private String imageUrl;
    private PictureType pictureType;
    private Set<String> styleTags;
}
