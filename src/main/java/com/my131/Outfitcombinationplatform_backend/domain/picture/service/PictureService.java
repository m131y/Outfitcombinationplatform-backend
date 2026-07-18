package com.my131.Outfitcombinationplatform_backend.domain.picture.service;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.my131.Outfitcombinationplatform_backend.domain.clothing.dto.ClothingItemDto;
import com.my131.Outfitcombinationplatform_backend.domain.clothing.entity.ClothingItem;
import com.my131.Outfitcombinationplatform_backend.domain.clothing.entity.ClothingItemCategory;
import com.my131.Outfitcombinationplatform_backend.domain.clothing.service.ClothingItemService;
import com.my131.Outfitcombinationplatform_backend.domain.picture.dto.PictureResponseDto;
import com.my131.Outfitcombinationplatform_backend.domain.picture.dto.UpdatePictureDto;
import com.my131.Outfitcombinationplatform_backend.domain.picture.dto.UploadPictureDto;
import com.my131.Outfitcombinationplatform_backend.domain.picture.entity.Picture;
import com.my131.Outfitcombinationplatform_backend.domain.picture.entity.PictureItem;
import com.my131.Outfitcombinationplatform_backend.domain.picture.repository.PictureItemRepository;
import com.my131.Outfitcombinationplatform_backend.domain.picture.repository.PictureRepository;
import com.my131.Outfitcombinationplatform_backend.domain.social.entity.Like;
import com.my131.Outfitcombinationplatform_backend.domain.user.dto.UserSummaryDto;
import com.my131.Outfitcombinationplatform_backend.domain.user.entity.User;
import com.my131.Outfitcombinationplatform_backend.domain.user.service.AuthService;
import com.my131.Outfitcombinationplatform_backend.global.enums.PictureType;
import com.my131.Outfitcombinationplatform_backend.global.exception.AuthenticationException;
import com.my131.Outfitcombinationplatform_backend.global.exception.ResourceNotFoundException;
import com.my131.Outfitcombinationplatform_backend.global.storage.StorageService;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class PictureService {
    private final PictureRepository pictureRepository;
    private final AuthService authService;
    private final StorageService storageService;
    private final ClothingItemService clothingItemService;
    private final PictureItemRepository pictureItemRepository;

    @Transactional
    public PictureResponseDto uploadPicture (UploadPictureDto uploadPictureDto, MultipartFile image) {
        User currentUser = authService.getCurrentUser();

        String imageUrl = storageService.upload(image, "pictures");

        Picture picture = Picture.builder()
                .user(currentUser)
                .pictureType(uploadPictureDto.getPictureType())
                .description(uploadPictureDto.getDescription())
                .imageUrl(imageUrl)
                .styleTags(uploadPictureDto.getStyleTags())
                .build();

        picture = pictureRepository.save(picture);

        if (uploadPictureDto.getPictureType().equals(PictureType.ITEM)) {
            ClothingItemDto clothingItemDto = uploadPictureDto.getClothingItem();

            if (clothingItemDto == null) {
                throw new IllegalArgumentException("ITEM 타입 업로드 시 clothingItem 정보가 필요합니다.");
            }
            ClothingItem clothingItem = clothingItemService.createClothingItem(clothingItemDto, currentUser);
            pictureItemRepository.save(PictureItem.of(picture, clothingItem));
        }

        return PictureResponseDto.fromEntity(picture);
    }

    public PictureResponseDto updatePicture (Long pictureId, UpdatePictureDto updatePictureDto) {
        User currentUser = authService.getCurrentUser();
        Picture picture = pictureRepository.findById(pictureId)
                .orElseThrow(() -> new ResourceNotFoundException("해당 사진을 찾을 수 없습니다."));

        if (!picture.getUser().getId().equals(currentUser.getId())) {
            throw new AuthenticationException("본인이 업로드 한 사진만 수정 가능합니다.");
        }

        picture.setDescription(updatePictureDto.getDescription());
        picture.setImageUrl(updatePictureDto.getImageUrl());
        picture.setPictureType(updatePictureDto.getPictureType());
        picture.setStyleTags(updatePictureDto.getStyleTags());

        Picture updatedPicture = pictureRepository.save(picture);

        return PictureResponseDto.fromEntity(updatedPicture);
    }

    public void deletePicture (Long pictureId) {
        User currentUser = authService.getCurrentUser();
        Picture picture = pictureRepository.findById(pictureId)
                .orElseThrow(() -> new ResourceNotFoundException("해당 사진을 찾을 수 없습니다."));

        if (!picture.getUser().getId().equals(currentUser.getId())) {
            throw new AuthenticationException("본인이 업로드 한 사진만 삭제 가능합니다.");
        }

        pictureRepository.delete(picture);
    }
}
