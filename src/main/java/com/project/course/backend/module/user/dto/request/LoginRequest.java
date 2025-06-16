package com.project.course.backend.module.user.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginRequest {

    @Schema(example = "admin@gmail.com")
    private String email;

    @Schema(example = "1234")
    private String password;

}

