package com.my131.Outfitcombinationplatform_backend.domain.user.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class FindPasswordDto {
    private String username;
    private String email;
}
