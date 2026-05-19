package com.my131.Outfitcombinationplatform_backend.domain.clothing.repository;

import com.my131.Outfitcombinationplatform_backend.domain.clothing.entity.ClothingItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClothingItemRepository extends JpaRepository<ClothingItem, Long> {
}
