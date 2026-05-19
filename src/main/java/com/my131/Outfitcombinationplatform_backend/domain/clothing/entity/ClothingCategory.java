package com.my131.Outfitcombinationplatform_backend.domain.clothing.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class ClothingCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code;        // "011", "021" 등 듀이 코드
    private String name;        // "반팔 티셔츠", "반팔 셔츠"

    // 부모 카테고리 (상위 분류)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private ClothingCategory parent;

    // 자식 카테고리들
    @OneToMany(mappedBy = "parent")
    private List<ClothingCategory> children = new ArrayList<>();

    private int depth;          // 0: 대분류, 1: 중분류, 2: 소분류
}