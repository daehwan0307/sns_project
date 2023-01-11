package com.example.sns_project.domain.dto;

import com.example.sns_project.exception.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ErrorResponse {
    private final ErrorCode errorCode;
    private final String message;
}
