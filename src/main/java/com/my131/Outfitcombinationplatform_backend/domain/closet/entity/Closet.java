package com.my131.Outfitcombinationplatform_backend.domain.closet.entity;

import com.my131.Outfitcombinationplatform_backend.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Closet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "closet", cascade = CascadeType.ALL)
    private List<ClosetItem> items = new ArrayList<>();
}
