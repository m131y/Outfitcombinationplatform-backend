package com.my131.Outfitcombinationplatform_backend.domain.user.service;

import com.my131.Outfitcombinationplatform_backend.domain.user.dto.AuthResponseDto;
import com.my131.Outfitcombinationplatform_backend.domain.user.dto.LoginRequestDto;
import com.my131.Outfitcombinationplatform_backend.domain.user.dto.SignupRequestDto;
import com.my131.Outfitcombinationplatform_backend.domain.user.dto.UserSummaryDto;
import com.my131.Outfitcombinationplatform_backend.domain.user.entity.User;
import com.my131.Outfitcombinationplatform_backend.domain.user.repository.UserRepository;
import com.my131.Outfitcombinationplatform_backend.domain.user.security.JwtUtil;
import com.my131.Outfitcombinationplatform_backend.global.enums.AuthProvider;
import com.my131.Outfitcombinationplatform_backend.global.exception.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    public AuthResponseDto signup(SignupRequestDto requestDto) {
        if (userRepository.existsByEmail(requestDto.getEmail())) {
            throw new UserAlreadyExistsException("동일한 email이 존재합니다.");
        }

        if (userRepository.existsByUsername(requestDto.getUsername())) {
            throw new UserAlreadyExistsException("동일한 email이 존재합니다.");
        }

        User user = User.builder()
                .username(requestDto.getUsername())
                .email(requestDto.getEmail())
                .password(passwordEncoder.encode(requestDto.getPassword()))
                .nickname(requestDto.getNickname())
                .authProvider(AuthProvider.LOCAL)
                .build();

        user = userRepository.save(user);

        String jwtToken = jwtUtil.generateToken(user);
        String refreshToken = jwtUtil.generateRefreshToken(user);

        return AuthResponseDto.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .user(UserSummaryDto.fromEntity(user))
                .build();
    }

    public AuthResponseDto login(LoginRequestDto requestDto) {
        try {
            String loginId = requestDto.getEmail() != null ? requestDto.getEmail() : requestDto.getUsername();
            log.info("loginId:");
            log.info(loginId);

            User user = userRepository.findByEmail(loginId)
                    .or(() -> userRepository.findByUsername(loginId))
                    .orElseThrow(() -> new AuthenticationException("로그인 ID가 유효하지 않습니다."));

            log.info("username:");
            log.info(user.getUsername());
            log.info(user.getEmail());
            log.info(user.getNickname());
            log.info(String.valueOf(user.getCreatedAt()));

            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            user.getUsername(),
                            requestDto.getPassword()
                    )
            );

            String jwtToken = jwtUtil.generateToken(user);
            String refreshToken = jwtUtil.generateRefreshToken(user);

            return AuthResponseDto.builder()
                    .accessToken(jwtToken)
                    .refreshToken(refreshToken)
                    .user(UserSummaryDto.fromEntity(user))
                    .build();

        } catch (BadRequestException e) {
            throw new AuthenticationException("로그인 ID와 비밀번호가 유효하지 않습니다.");
        }
    }

    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || authentication.getPrincipal() == null) {
            throw new ResourceNotFoundException("인증된 사용자를 찾을 수 없습니다.");
        }

        String username;

        if (authentication.getPrincipal() instanceof User userPrincipal) {
            username = userPrincipal.getUsername();
        } else if (authentication.getPrincipal() instanceof UserDetails userDetails) {
            username = userDetails.getUsername();
        } else {
            username =authentication.getName();
        }

        return userRepository.findByUsername(username)
                .or(() -> userRepository.findByEmail(username))
                .orElseThrow(() -> new ResourceNotFoundException("사용자를 찾을 수 없습니다. : " + username));
    }
}
