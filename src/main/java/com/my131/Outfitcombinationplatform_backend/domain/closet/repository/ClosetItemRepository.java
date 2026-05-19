package com.my131.Outfitcombinationplatform_backend.domain.closet.repository;

import com.my131.Outfitcombinationplatform_backend.domain.closet.entity.ClosetItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClosetItemRepository extends JpaRepository<ClosetItem, Long> {
}
