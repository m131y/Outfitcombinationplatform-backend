package com.my131.Outfitcombinationplatform_backend.domain.recommendation.repository;

import com.my131.Outfitcombinationplatform_backend.domain.recommendation.entity.OutfitItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OutfitItemRepository extends JpaRepository<OutfitItem, Long> {
}
