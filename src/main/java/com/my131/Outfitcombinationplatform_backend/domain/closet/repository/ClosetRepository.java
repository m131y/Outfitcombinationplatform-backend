package com.my131.Outfitcombinationplatform_backend.domain.closet.repository;

import com.my131.Outfitcombinationplatform_backend.domain.closet.entity.Closet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClosetRepository extends JpaRepository<Closet, Long> {
    boolean existsByUserId(Long userId);
    boolean existsByIdAndUserId(Long id, Long userId);
}
