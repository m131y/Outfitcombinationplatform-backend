package com.my131.Outfitcombinationplatform_backend.domain.user.dto;

import com.my131.Outfitcombinationplatform_backend.domain.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class UserSummaryDto {
    private Long id;
    private String nickname;
    private String profileImageUrl;

    public static UserSummaryDto fromEntity(User user) {
        return UserSummaryDto.builder()
                .id(user.getId())
                .nickname(user.getNickname())
                .profileImageUrl(user.getProfileImageUrl())
                .build();
    }
}