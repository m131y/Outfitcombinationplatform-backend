package com.my131.Outfitcombinationplatform_backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // CSRF 비활성화 (REST API + H2 콘솔)
                .csrf(csrf -> csrf.disable())

                // H2 콘솔 iframe 허용
                .headers(headers -> headers
                        .frameOptions(frame -> frame.disable())
                )

                // 세션 사용 안 함 (JWT 방식 대비)
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )

                // 요청 권한 설정
                .authorizeHttpRequests(auth -> auth
                                // H2 콘솔
                                .requestMatchers("/h2-console/**").permitAll()

                                // 인증 관련 엔드포인트 (나중에 만들 것들)
                                .requestMatchers("/api/auth/**").permitAll()

                                // OAuth2 콜백
                                .requestMatchers("/login/oauth2/**", "/oauth2/**").permitAll()

                                // 파일 업로드 / AI API → 나중에 인증 필요하면 여기서 수정
                                .requestMatchers("/api/upload/**").permitAll()   // S3 업로드
                                .requestMatchers("/api/ai/**").permitAll()       // AI API

                                // 지금은 개발용이라 전체 허용
                                .anyRequest().permitAll()
                        // ↑ 나중에 인증 적용할 때 아래로 교체
                        // .anyRequest().authenticated()
                );

        // OAuth2 로그인 (나중에 활성화)
        // .oauth2Login(oauth2 -> oauth2
        //     .loginPage("/login")
        //     .defaultSuccessUrl("/api/auth/oauth2/success")
        // )

        // JWT 필터 (나중에 추가)
        // .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)

        return http.build();
    }

    // 비밀번호 암호화 (로컬 로그인용)
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // AuthenticationManager (로컬 로그인용)
    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}