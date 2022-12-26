package com.example.sns_project.controller;

import com.example.sns_project.domain.dto.Response;
import com.example.sns_project.domain.dto.UserJoinRequest;
import com.example.sns_project.domain.dto.UserJoinResponse;
import com.example.sns_project.domain.dto.UserLoginRequest;
import com.example.sns_project.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    @PostMapping("/join")
    public ResponseEntity<Response> join(@RequestBody UserJoinRequest dto){
        UserJoinResponse userJoinResponse = userService.join(dto.getUserName(), dto.getPassword());
        return ResponseEntity.ok().body(Response.success(userJoinResponse));
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserLoginRequest dto){
        String token = userService.login(dto.getUserName(),dto.getPassword());
        return ResponseEntity.ok().body(token);
    }
}
