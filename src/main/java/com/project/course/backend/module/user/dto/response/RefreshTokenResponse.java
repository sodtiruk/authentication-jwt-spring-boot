package com.project.course.backend.module.user.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RefreshTokenResponse {
    private String newAccessToken;
}
