package com.my131.Outfitcombinationplatform_backend.domain.social.repository;

import com.my131.Outfitcombinationplatform_backend.domain.picture.entity.Picture;
import com.my131.Outfitcombinationplatform_backend.domain.social.entity.Like;
import com.my131.Outfitcombinationplatform_backend.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface LikeRepository extends JpaRepository<Like, Long> {
    @Query("SELECT COUNT(l) FROM Like l WHERE l.picture.id = :pictureId")
    Long countByPictureId(@Param("pictureId") Long pictureId);

    Long countByUserId(Long userId);

    boolean existsByUserAndPicture(User user, Picture picture);

    void deleteByUserAndPicture(User user, Picture picture);
}
