package com.my131.Outfitcombinationplatform_backend.domain.user.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginRequestDto {
    private String username;
    private String email;

    @NotBlank(message = "비밀번호를 입력해주세요.")
    private String password;
}
