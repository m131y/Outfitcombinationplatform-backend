package com.my131.Outfitcombinationplatform_backend.domain.closet.entity;

import com.my131.Outfitcombinationplatform_backend.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Closet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Builder.Default
    @OneToMany(mappedBy = "closet", cascade = CascadeType.ALL)
    private List<ClosetItem> items = new ArrayList<>();
}
