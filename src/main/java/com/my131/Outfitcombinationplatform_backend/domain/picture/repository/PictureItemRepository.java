package com.my131.Outfitcombinationplatform_backend.domain.picture.repository;

import com.my131.Outfitcombinationplatform_backend.domain.picture.entity.PictureItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PictureItemRepository extends JpaRepository<PictureItem, Long> {
}
