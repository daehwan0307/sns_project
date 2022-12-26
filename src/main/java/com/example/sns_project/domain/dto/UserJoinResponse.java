package com.example.sns_project.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Builder
public class UserJoinResponse {
    private Long id;
    private String userName;
}


