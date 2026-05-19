package com.my131.Outfitcombinationplatform_backend.domain.clothing.entity;

import com.my131.Outfitcombinationplatform_backend.global.common.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@NoArgsConstructor
public class ClothingItem extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String imageUrl;
    private String brand;
    private String purchaseUrl;

    // 기존 단일 category 필드 삭제하고 다중 분류로 교체
    @OneToMany(mappedBy = "clothingItem", cascade = CascadeType.ALL)
    private List<ClothingItemCategory> categories = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "clothing_item_tags")
    private Set<String> styleTags = new HashSet<>();
}