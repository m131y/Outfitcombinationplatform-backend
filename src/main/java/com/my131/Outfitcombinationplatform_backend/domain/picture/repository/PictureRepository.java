package com.my131.Outfitcombinationplatform_backend.domain.picture.repository;

import com.my131.Outfitcombinationplatform_backend.domain.picture.entity.Picture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PictureRepository extends JpaRepository<Picture, Long> {
}
