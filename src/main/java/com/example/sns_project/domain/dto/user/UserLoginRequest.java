package com.example.sns_project.domain.dto.user;


import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserLoginRequest {
    private String userName;
    private String password;
}
