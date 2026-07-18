package com.my131.Outfitcombinationplatform_backend.domain.user.controller;

import com.my131.Outfitcombinationplatform_backend.domain.user.dto.AuthResponseDto;
import com.my131.Outfitcombinationplatform_backend.domain.user.dto.LoginRequestDto;
import com.my131.Outfitcombinationplatform_backend.domain.user.dto.SignupRequestDto;
import com.my131.Outfitcombinationplatform_backend.domain.user.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<AuthResponseDto> signup(@Valid @RequestBody SignupRequestDto requestDto) {
        return ResponseEntity.ok(authService.signup(requestDto));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> login(@Valid @RequestBody LoginRequestDto requestDto) {
        return ResponseEntity.ok(authService.login(requestDto));
    }
}
