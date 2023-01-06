package com.example.sns_project.controller;

import com.example.sns_project.domain.dto.*;
import com.example.sns_project.domain.dto.user.UserJoinRequest;
import com.example.sns_project.domain.dto.user.UserJoinResponse;
import com.example.sns_project.domain.dto.user.UserLoginRequest;
import com.example.sns_project.domain.dto.user.UserLoginResponse;
import com.example.sns_project.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
@Api(tags = {"User API"})
public class UserController {

    private final UserService userService;

    @ApiOperation(value = "사용자 회원가입", notes = "회원가입을 합니다.")
    @PostMapping("/join")
    public ResponseEntity<Response> join(@RequestBody UserJoinRequest dto){
        UserJoinResponse userJoinResponse = userService.join(dto.getUserName(), dto.getPassword());
        return ResponseEntity.ok().body(Response.success(userJoinResponse));
    }

    @ApiOperation(value = "사용자 로그인", notes = "UserName과 UserPassword를 이용하여 사용자 정보를 조회합니다.")
    @PostMapping("/login")
    public ResponseEntity<Response> login(@RequestBody UserLoginRequest dto){

        UserLoginResponse userLoginResponse = userService.login(dto.getUserName(), dto.getPassword());
        return ResponseEntity.ok().body(Response.success(userLoginResponse));
    }

    @ApiOperation(value = "사용자 권한 변경", notes = "USER의 권한을 ADMIN으로 변경합니다.")
    @PostMapping("/{id}/role/change")
    public String userRoleChange(@PathVariable Long id){

       return userService.userRoleChange(id);
    }
}
