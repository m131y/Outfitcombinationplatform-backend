package com.my131.Outfitcombinationplatform_backend.domain.recommendation.entity;

import com.my131.Outfitcombinationplatform_backend.domain.clothing.entity.ClothingItem;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter @NoArgsConstructor
public class OutfitItem {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private OutfitRecommendation recommendation;

    @ManyToOne(fetch = FetchType.LAZY)
    private ClothingItem clothingItem;

    private boolean userOwns; // 사용자가 보유 중인지 여부 (false면 쇼핑링크 노출)
}
