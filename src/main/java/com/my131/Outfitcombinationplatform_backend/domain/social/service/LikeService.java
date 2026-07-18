package com.my131.Outfitcombinationplatform_backend.domain.social.service;

import com.my131.Outfitcombinationplatform_backend.domain.picture.entity.Picture;
import com.my131.Outfitcombinationplatform_backend.domain.picture.repository.PictureRepository;
import com.my131.Outfitcombinationplatform_backend.domain.social.entity.Like;
import com.my131.Outfitcombinationplatform_backend.domain.social.repository.LikeRepository;
import com.my131.Outfitcombinationplatform_backend.domain.user.entity.User;
import com.my131.Outfitcombinationplatform_backend.domain.user.service.AuthService;
import com.my131.Outfitcombinationplatform_backend.global.exception.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LikeService {
    private final LikeRepository likeRepository;
    private final PictureRepository pictureRepository;
    private final AuthService authService;

    public boolean toggleLike(Long pictureId) {
        User user = authService.getCurrentUser();
        Picture picture = pictureRepository.findById(pictureId)
                .orElseThrow(()-> new BadRequestException("해당 사진을 찾을 수 없습니다."));

        boolean alreadyLiked = likeRepository.existsByUserAndPicture(user, picture);

        if (alreadyLiked) {
            likeRepository.deleteByUserAndPicture(user, picture);
            return false;
        } else {
            Like like = Like.builder()
                    .picture(picture)
                    .user(user)
                    .build();

            likeRepository.save(like);
            return true;
        }
    }
    @Transactional(readOnly = true)
    public Long getLikeCount(Long pictureId) {
        return likeRepository.countByPictureId(pictureId);
    }

    @Transactional(readOnly = true)
    public boolean isLikedByCurrentUser(Long pictureId) {
        User currentUser = authService.getCurrentUser();

        Picture picture = pictureRepository.findById(pictureId)
                .orElseThrow(()-> new BadRequestException("해당 사진을 찾을 수 없습니다."));

        return likeRepository.existsByUserAndPicture(currentUser, picture);
    }
}
