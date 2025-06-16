package com.project.course.backend.module.user.service;

import com.project.course.backend.common.component.DtoEntityMapper;
import com.project.course.backend.module.user.component.JwtComponent;
import com.project.course.backend.module.user.dto.request.LoginRequest;
import com.project.course.backend.module.user.dto.request.RegisterRequest;
import com.project.course.backend.module.user.dto.response.LoginResponse;
import com.project.course.backend.module.user.dto.response.RefreshTokenResponse;
import com.project.course.backend.module.user.dto.response.RegisterResponse;
import com.project.course.backend.module.user.entity.UserEntity;
import com.project.course.backend.module.user.repository.AuthRepository;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {

    private final AuthRepository authRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtComponent jwtComponent;

    public static final String USER_ID = "userId";
    public static final String EMAIL = "email";

    public LoginResponse login(HttpServletResponse response, LoginRequest loginRequest) {
        UserEntity userEntity = authRepository.findByEmail(loginRequest.getEmail()).
                orElseThrow(() -> new UsernameNotFoundException("Email not found"));

        if (userEntity == null || !passwordEncoder.matches(loginRequest.getPassword(), userEntity.getPassword())) {
            throw new RuntimeException("Invalid email or password");
        }

        Map<String, Object> claims = new HashMap<>();
        claims.put(USER_ID, userEntity.getUserId());
        claims.put(EMAIL, loginRequest.getEmail());
        String accessToken = jwtComponent.generateAccessToken(claims);
        String refreshToken = jwtComponent.generateRefreshToken(claims);

        // Set the refresh token in a secure cookie
        jwtComponent.setRefreshTokenCookie(response, refreshToken);

        return LoginResponse.builder()
                .accessToken(accessToken)
                .build();
    }

    public RegisterResponse register(RegisterRequest registerRequest) {
        // Encode the password before saving
        registerRequest.setPassword(passwordEncoder.encode(registerRequest.getPassword()));

        UserEntity userEntity = DtoEntityMapper.mapToEntity(registerRequest, UserEntity.class);

        try {
            return DtoEntityMapper.mapToDto(
                    authRepository.save(userEntity), RegisterResponse.class);

        } catch (Exception e) {
            throw new RuntimeException("Error while registering user: " + e.getMessage());
        }
    }


    public RefreshTokenResponse refreshToken(String refreshToken, HttpServletResponse response) {
        if (refreshToken == null || !jwtComponent.validateToken(refreshToken)) {
            throw new RuntimeException("Invalid or expired refresh token");
        }

        Map<String, Object> claims = jwtComponent.extractClaims(refreshToken);
        Long userId = Long.valueOf(claims.get(USER_ID).toString());
        String email = claims.get(EMAIL).toString();

        Map<String, Object> newClaims = new HashMap<>();
        newClaims.put(USER_ID, userId);
        newClaims.put(EMAIL, email);

        String newAccessToken = jwtComponent.generateAccessToken(newClaims);
        String newRefreshToken = jwtComponent.generateRefreshToken(newClaims);

        // Set the refresh token in a secure cookie
        jwtComponent.setRefreshTokenCookie(response, newRefreshToken);

        return RefreshTokenResponse.builder()
                .newAccessToken(newAccessToken)
                .build();
    }
}
