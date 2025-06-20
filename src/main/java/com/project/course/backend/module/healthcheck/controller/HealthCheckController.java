package com.project.course.backend.module.healthcheck.controller;

import com.project.course.backend.common.dto.BaseResponse;
import com.project.course.backend.module.user.exception.TestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.project.course.backend.common.constant.CommonConstant.ErrorMessage.*;
import static com.project.course.backend.common.constant.CommonConstant.StatusCode.*;

@RestController
@RequestMapping("/api/v1")
public class HealthCheckController {

    @PostMapping("/health")
    public ResponseEntity<BaseResponse<String>> healthCheck() {
        return ResponseEntity.status(200).body(BaseResponse.<String>builder()
                    .data("Service is running")
                    .message(SUCCESS)
                    .statusCode(SUCCESS_CODE)
                    .build());
    }

    @GetMapping("/profile")
    public String getUserProfile() {
        throw new TestException("this is a test exception");
    }

}
