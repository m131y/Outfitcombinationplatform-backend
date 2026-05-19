package com.my131.Outfitcombinationplatform_backend.domain.user.entity;

import com.my131.Outfitcombinationplatform_backend.domain.closet.entity.Closet;
import com.my131.Outfitcombinationplatform_backend.domain.post.entity.Post;
import com.my131.Outfitcombinationplatform_backend.domain.social.entity.Like;
import com.my131.Outfitcombinationplatform_backend.global.common.BaseTimeEntity;
import com.my131.Outfitcombinationplatform_backend.global.enums.AuthProvider;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.core.annotation.MergedAnnotations;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

@Entity
@Table(name = "users")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseTimeEntity implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(name = "nickname")
    private String nickname;

    private String bio;

    @Column(name = "profile_image_url", columnDefinition = "TEXT")
    private String profileImageUrl;

    @Enumerated(EnumType.STRING)
    private AuthProvider provider;

    private String providerId;

    @Column(nullable = false)
    @Builder.Default
    private BigDecimal totalPoints = BigDecimal.ZERO; // 판매자에게 정산된 총 적립금

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    private boolean enabled;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @Builder.Default
    private List<Post> posts = new ArrayList<>();

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    @Builder.Default
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

    public void addPoints(BigDecimal amount) {
        this.totalPoints = this.totalPoints.add(amount);
    }
}