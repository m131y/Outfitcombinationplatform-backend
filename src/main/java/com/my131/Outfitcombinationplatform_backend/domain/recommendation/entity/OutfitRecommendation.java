package com.my131.Outfitcombinationplatform_backend.domain.recommendation.entity;


import com.my131.Outfitcombinationplatform_backend.domain.clothing.entity.ClothingItem;
import com.my131.Outfitcombinationplatform_backend.domain.user.entity.User;
import com.my131.Outfitcombinationplatform_backend.global.common.BaseTimeEntity;
import com.my131.Outfitcombinationplatform_backend.global.enums.RecommendationSource;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class OutfitRecommendation extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private String description; // AI가 생성한 코멘트

    @OneToMany(mappedBy = "recommendation", cascade = CascadeType.ALL)
    private List<OutfitItem> items = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private RecommendationSource source; // FROM_OOTD, AI_GENERATED
}

