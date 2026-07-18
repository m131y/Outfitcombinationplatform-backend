package com.my131.Outfitcombinationplatform_backend.domain.picture.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.my131.Outfitcombinationplatform_backend.domain.social.entity.Like;
import com.my131.Outfitcombinationplatform_backend.domain.user.entity.User;
import com.my131.Outfitcombinationplatform_backend.global.common.BaseTimeEntity;
import com.my131.Outfitcombinationplatform_backend.global.enums.PictureType;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "pictures")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Picture extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private String description;

    @Column(name = "image_url", columnDefinition = "TEXT")
    private String imageUrl;

    @JsonIgnore
    @OneToMany(mappedBy = "picture", fetch = FetchType.LAZY)
    @Builder.Default
    private Set<Like> likes = new HashSet<>();

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PictureType pictureType;

    @Builder.Default
    private Set<String> styleTags = new HashSet<>();
}
