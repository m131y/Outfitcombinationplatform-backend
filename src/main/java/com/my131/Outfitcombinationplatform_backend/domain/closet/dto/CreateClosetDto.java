package com.my131.Outfitcombinationplatform_backend.domain.closet.dto;

import com.my131.Outfitcombinationplatform_backend.domain.user.entity.User;
import lombok.Builder;
import lombok.Data;


@Builder
@Data
public class CreateClosetDto {
    private User user;
}
