package com.my131.Outfitcombinationplatform_backend.domain.picture.controller;

import com.my131.Outfitcombinationplatform_backend.domain.picture.dto.PictureResponseDto;
import com.my131.Outfitcombinationplatform_backend.domain.picture.dto.UpdatePictureDto;
import com.my131.Outfitcombinationplatform_backend.domain.picture.dto.UploadPictureDto;
import com.my131.Outfitcombinationplatform_backend.domain.picture.service.PictureService;
import com.my131.Outfitcombinationplatform_backend.global.common.ApiResponse;
import com.my131.Outfitcombinationplatform_backend.global.enums.PictureType;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/pictures")
@RequiredArgsConstructor
public class PictureController {
    private final PictureService pictureService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ApiResponse<PictureResponseDto>> uploadPicture(
            @RequestPart("data") @Valid UploadPictureDto uploadDto,
            @RequestPart("image") MultipartFile image) {

        // ITEM 타입인데 clothingItem 정보가 없으면 400
        if (uploadDto.getPictureType() == PictureType.ITEM
                && uploadDto.getClothingItem() == null) {
            throw new IllegalArgumentException("ITEM 타입은 clothingItem 정보가 필요합니다");
        }

        PictureResponseDto response = pictureService.uploadPicture(uploadDto, image); // username = email
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(response));
    }

    @PutMapping("/{pictureId}")
    public ResponseEntity<PictureResponseDto> updatePicture (@PathVariable Long pictureId, UpdatePictureDto updatePictureDto) {
        return ResponseEntity.ok(pictureService.updatePicture(pictureId, updatePictureDto));
    }

    @DeleteMapping("/{pictureId}")
    public ResponseEntity<Void> deletePicture (@PathVariable Long pictureId) {
        pictureService.deletePicture(pictureId);
        return ResponseEntity.noContent().build();
    }
}
