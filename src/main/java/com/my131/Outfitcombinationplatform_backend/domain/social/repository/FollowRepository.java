package com.my131.Outfitcombinationplatform_backend.domain.social.repository;

import com.my131.Outfitcombinationplatform_backend.domain.social.entity.Follow;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FollowRepository extends JpaRepository<Follow, Long> {
}
