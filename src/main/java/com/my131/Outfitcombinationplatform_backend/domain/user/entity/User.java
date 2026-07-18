package com.my131.Outfitcombinationplatform_backend.domain.user.entity;


import com.my131.Outfitcombinationplatform_backend.domain.closet.entity.Closet;
import com.my131.Outfitcombinationplatform_backend.domain.clothing.entity.ClothingItem;
import com.my131.Outfitcombinationplatform_backend.domain.picture.entity.Picture;
import com.my131.Outfitcombinationplatform_backend.domain.post.entity.Post;
import com.my131.Outfitcombinationplatform_backend.domain.social.entity.Like;
import com.my131.Outfitcombinationplatform_backend.global.common.BaseTimeEntity;
import com.my131.Outfitcombinationplatform_backend.global.enums.AuthProvider;
import jakarta.persistence.*;          // ✅ @Id, @Column, @Entity 등 전부 여기서
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.math.BigDecimal;
import java.util.*;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User extends BaseTimeEntity implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(unique = true, nullable = false)
    private String email;

    private String password;

    @Column(name = "nickname", nullable = false)
    private String nickname;

    private String bio;

    @Column(name = "profile_image_url", columnDefinition = "TEXT")
    private String profileImageUrl;

    @Enumerated(EnumType.STRING)
    private AuthProvider authProvider;

    private String providerId;

    private boolean enabled;

    @Builder.Default
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Post> posts = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Picture> pictures = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<ClothingItem> clothingItems = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private Set<Like> likes = new HashSet<>();

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Closet closet;

//    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
//    @Builder.Default
//    private Set<MergedAnnotations.Search> searches = new HashSet<>();

    @PrePersist
    protected void onCreate() { enabled = true; }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getUsername() {
        return email;
    }

    public String getRealUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() { return true; }

    @Override
    public boolean isAccountNonLocked() { return true; }

    @Override
    public boolean isCredentialsNonExpired() { return true; }

    @Override
    public boolean isEnabled() { return true; }

    @Builder
    public User(String email, String password, String nickname, AuthProvider authProvider) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.authProvider = authProvider;
    }
}