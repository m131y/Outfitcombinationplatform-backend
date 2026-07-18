package com.my131.Outfitcombinationplatform_backend.domain.user.controller;

import com.my131.Outfitcombinationplatform_backend.domain.user.dto.AuthResponseDto;
import com.my131.Outfitcombinationplatform_backend.domain.user.dto.UpdatePasswordDto;
import com.my131.Outfitcombinationplatform_backend.domain.user.dto.UserDto;
import com.my131.Outfitcombinationplatform_backend.domain.user.entity.User;
import com.my131.Outfitcombinationplatform_backend.domain.user.service.AuthService;
import com.my131.Outfitcombinationplatform_backend.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;
    private final AuthService authService;

    @GetMapping("/me")
    public ResponseEntity<UserDto> getCurrentUser() {
        return ResponseEntity.ok(UserDto.fromEntity(authService.getCurrentUser()));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUserProfile(@PathVariable Long userId) {
        return ResponseEntity.ok(UserDto.fromEntity(userService.getUserProfile(userId)));
    }

    @PutMapping("/me/password")
    public ResponseEntity<Boolean> updatePassword(UpdatePasswordDto requestDto) {
        return ResponseEntity.ok(userService.updatePassword(requestDto));
    }
}
