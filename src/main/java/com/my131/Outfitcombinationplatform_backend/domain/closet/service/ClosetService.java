package com.my131.Outfitcombinationplatform_backend.domain.closet.service;

import com.my131.Outfitcombinationplatform_backend.domain.closet.dto.CreateClosetDto;
import com.my131.Outfitcombinationplatform_backend.domain.closet.entity.Closet;
import com.my131.Outfitcombinationplatform_backend.domain.closet.repository.ClosetRepository;
import com.my131.Outfitcombinationplatform_backend.domain.user.entity.User;
import com.my131.Outfitcombinationplatform_backend.domain.user.service.AuthService;
import com.my131.Outfitcombinationplatform_backend.global.exception.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class ClosetService {
    private final ClosetRepository closetRepository;
    private final AuthService authService;

    public void createCloset() {
        User user = authService.getCurrentUser();
        if (!closetRepository.existsByUserId(user.getId())) {
            Closet closet = Closet.builder()
                    .user(user)
                    .items(new ArrayList<>())
                    .build();

            closetRepository.save(closet);
        } else {
            throw new BadRequestException("옷장이 이미 존재합니다.");
        }
    }

    public void deleteCloset(Long closetId) {
        User user = authService.getCurrentUser();
        if (closetRepository.existsByIdAndUserId(closetId, user.getId())){
            Closet closet = closetRepository.findById(closetId)
                    .orElseThrow(()-> new BadRequestException("옷장이 없습니다."));
            closet.setItems(new ArrayList<>());
        }
    }
}
