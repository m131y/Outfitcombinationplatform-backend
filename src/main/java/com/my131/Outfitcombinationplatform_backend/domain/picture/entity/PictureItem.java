package com.my131.Outfitcombinationplatform_backend.domain.picture.entity;

import com.my131.Outfitcombinationplatform_backend.domain.clothing.entity.ClothingItem;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class PictureItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "picture_id")
    private Picture picture;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "clothing_item_id")
    private ClothingItem clothingItem;

    public static PictureItem of(Picture picture, ClothingItem clothingItem) {
        PictureItem pi = new PictureItem();
        pi.picture = picture;
        pi.clothingItem = clothingItem;
        return pi;
    }
}