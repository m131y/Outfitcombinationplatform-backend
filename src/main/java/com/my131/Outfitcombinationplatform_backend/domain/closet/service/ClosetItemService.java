package com.my131.Outfitcombinationplatform_backend.domain.closet.service;

import com.my131.Outfitcombinationplatform_backend.domain.closet.entity.ClosetItem;
import com.my131.Outfitcombinationplatform_backend.domain.closet.repository.ClosetItemRepository;
import com.my131.Outfitcombinationplatform_backend.domain.clothing.entity.ClothingItem;
import com.my131.Outfitcombinationplatform_backend.domain.clothing.repository.ClothingItemRepository;
import com.my131.Outfitcombinationplatform_backend.domain.user.entity.User;
import com.my131.Outfitcombinationplatform_backend.domain.user.service.AuthService;
import com.my131.Outfitcombinationplatform_backend.global.enums.ClosetItemSource;
import com.my131.Outfitcombinationplatform_backend.global.exception.BadRequestException;
import com.my131.Outfitcombinationplatform_backend.global.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.my131.Outfitcombinationplatform_backend.global.enums.ClosetItemSource.FROM_POST;
import static com.my131.Outfitcombinationplatform_backend.global.enums.ClosetItemSource.UPLOADED;

@Service
@RequiredArgsConstructor
public class ClosetItemService {
    private final ClosetItemRepository closetItemRepository;
    private final ClothingItemRepository clothingItemRepository;
    private final AuthService authService;

    public void addClosetItem(Long clothingItemId){
        User user = authService.getCurrentUser();
        ClothingItem clothingItem = clothingItemRepository.findById(clothingItemId)
                .orElseThrow(()-> new ResourceNotFoundException("해당 clothingItem을 찾을 수 없습니다."));

        ClosetItemSource source;

        if (clothingItem.getUser().getId().equals(user.getId())) {
            source = UPLOADED;
        } else {
            source = FROM_POST;
        }

        ClosetItem closetItem = ClosetItem.builder()
                .closet(user.getCloset()) // 옷장 1인 1개. 추후 수정
                .clothingItem(clothingItem)
                .source(source)
                .build();

        closetItemRepository.save(closetItem);
    }

    public void deleteClosetItem(Long closetItemId){
        User user = authService.getCurrentUser();
        ClosetItem closetItem = closetItemRepository.findById(closetItemId)
                .orElseThrow(()-> new ResourceNotFoundException("해당 closetItem을 찾을 수 없습니다."));;

        if (!user.getId().equals(closetItem.getCloset().getUser().getId())) {
            throw new BadRequestException("본인 옷장에서만 삭제가 가능합니다.");
        }

        closetItemRepository.deleteById(closetItemId);
    }
}
