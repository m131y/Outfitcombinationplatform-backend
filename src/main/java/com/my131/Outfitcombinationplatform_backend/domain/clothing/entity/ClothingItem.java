package com.my131.Outfitcombinationplatform_backend.domain.clothing.entity;

import com.my131.Outfitcombinationplatform_backend.domain.clothing.dto.ClothingItemDto;
import com.my131.Outfitcombinationplatform_backend.domain.user.entity.User;
import com.my131.Outfitcombinationplatform_backend.global.common.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class ClothingItem extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private String name;
    private String imageUrl;
    private String brand;
    private String purchaseUrl;

    @Builder.Default
    @OneToMany(mappedBy = "clothingItem", cascade = CascadeType.ALL)
    private List<ClothingItemCategory> categories = new ArrayList<>();

    @Builder.Default
    @ElementCollection
    @CollectionTable(name = "clothing_item_tags")
    private Set<String> styleTags = new HashSet<>();

    public static ClothingItem of(ClothingItemDto dto, List<ClothingItemCategory> categories,
                                  User user, String imageUrl) {
        ClothingItem item = new ClothingItem();
        item.name = dto.getName();
        item.brand = dto.getBrand();
        item.purchaseUrl = dto.getPurchaseUrl();
        item.categories = categories;
        item.user = user;
        item.imageUrl = imageUrl;
        return item;
    }
}