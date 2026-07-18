package com.my131.Outfitcombinationplatform_backend.domain.picture.dto;

import com.my131.Outfitcombinationplatform_backend.domain.picture.entity.Picture;
import com.my131.Outfitcombinationplatform_backend.domain.user.entity.User;
import com.my131.Outfitcombinationplatform_backend.global.enums.PictureType;
import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Builder
@Data
public class PictureResponseDto {
    private String description;
    private String imageUrl;
    private Long likesCount;
    private PictureType pictureType;
    private Set<String> styleTags;

    public static PictureResponseDto fromEntity(Picture picture) {
        return PictureResponseDto.builder()
                .description(picture.getDescription())
                .imageUrl(picture.getImageUrl())
                .likesCount(picture.getLikes().stream().count())
                .pictureType(picture.getPictureType())
                .styleTags(picture.getStyleTags())
                .build();
    }
}
