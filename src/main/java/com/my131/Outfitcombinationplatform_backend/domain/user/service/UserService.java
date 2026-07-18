package com.my131.Outfitcombinationplatform_backend.domain.user.service;

import com.my131.Outfitcombinationplatform_backend.domain.user.dto.FindPasswordDto;
import com.my131.Outfitcombinationplatform_backend.domain.user.dto.UpdatePasswordDto;
import com.my131.Outfitcombinationplatform_backend.domain.user.entity.User;
import com.my131.Outfitcombinationplatform_backend.domain.user.repository.UserRepository;
import com.my131.Outfitcombinationplatform_backend.global.exception.AuthenticationException;
import com.my131.Outfitcombinationplatform_backend.global.exception.InvalidPasswordException;
import com.my131.Outfitcombinationplatform_backend.global.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final AuthService authService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User getUserProfile(Long userId) {
        User currentUser = authService.getCurrentUser();
        User targetUser = userRepository.findById(userId)
                .orElseThrow(()-> new ResourceNotFoundException("사용자를 찾을 수 없습니다. : " + userId));

        return targetUser;
    }

    public boolean updatePassword(UpdatePasswordDto updatePasswordDto) {
        User currentUser = authService.getCurrentUser();

        if (passwordEncoder.matches(updatePasswordDto.getUpdatePassword(), currentUser.getPassword())) {
            throw new InvalidPasswordException("바꿀 비밀번호가 현재 비밀번호와 동일합니다.");
        }

        if (passwordEncoder.matches(updatePasswordDto.getCurrentPassword(), currentUser.getPassword())) {
            throw new InvalidPasswordException("현재 비밀번호가 동일하지 않습니다.");
        }

        currentUser.setPassword(passwordEncoder.encode(updatePasswordDto.getUpdatePassword()));

        User savedUser = userRepository.save(currentUser);
        return true;
    }

    public String findPassword(FindPasswordDto findPasswordDto) {
        String loginId = findPasswordDto.getEmail() != null ? findPasswordDto.getEmail() : findPasswordDto.getUsername();

        User user = userRepository.findByEmail(loginId)
                .or(() -> userRepository.findByUsername(loginId))
                .orElseThrow(() -> new AuthenticationException("로그인 ID가 유효하지 않습니다."));

        return user.getPassword();
    }
}
