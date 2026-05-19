package com.my131.Outfitcombinationplatform_backend.domain.social.entity;

import com.my131.Outfitcombinationplatform_backend.domain.picture.entity.Picture;
import com.my131.Outfitcombinationplatform_backend.domain.user.entity.User;
import com.my131.Outfitcombinationplatform_backend.global.common.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "likes")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Like extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "picture_id", nullable = false)
    private Picture picture;

}
