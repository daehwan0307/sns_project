package com.example.sns_project.controller;

import com.example.sns_project.domain.dto.user.UserJoinRequest;
import com.example.sns_project.domain.dto.user.UserLoginRequest;
import com.example.sns_project.exception.AppException;
import com.example.sns_project.exception.ErrorCode;
import com.example.sns_project.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class UserControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    UserService userService;
    @Autowired
    ObjectMapper objectMapper;

    @Test
    @DisplayName("회원가입 성공")
    @WithMockUser
    void join() throws Exception {
        String userName="daehwan";
        String password="eoghks";

        mockMvc.perform(post("/api/v1/users/join")
                        .with(csrf()) //spring security test repository 추가해야함
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(new UserJoinRequest(userName,password))))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("회원가입 실패 -userName 중복")
    @WithMockUser
    void join_fail() throws Exception {
        String userName="daehwan";
        String password="eoghks";

        when(userService.join(any(),any()))
                .thenThrow(new RuntimeException("해당 userId가 중복됩니다."));

        mockMvc.perform(post("/api/v1/users/join")
                        .with(csrf()) //spring security test repository 추가해야함
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(new UserJoinRequest(userName,password))))
                .andDo(print())
                .andExpect(status().isConflict());
    }

    @Test
    @DisplayName("로그인 성공")
    @WithMockUser
    void login_success() throws Exception{
        String userName="daehwan";
        String password="eoghks";

       //when(userService.login(any(),any()))
        //        .thenReturn("token");

        mockMvc.perform(post("/api/v1/users/login")
                        .with(csrf()) //spring security test repository 추가해야함
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(new UserLoginRequest(userName,password))))
                .andDo(print())
                .andExpect(status().isOk());
    }
    @Test
    @DisplayName("로그인 실패-userName 없음")
    @WithMockUser
    void login_fail1() throws Exception{
        String userName="daehwan";
        String password="eoghks";

        when(userService.login(any(),any()))
                .thenThrow(new AppException(ErrorCode.USERNAME_NOT_FOUND,""));

        mockMvc.perform(post("/api/v1/users/login")
                        .with(csrf()) //spring security test repository 추가해야함
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(new UserLoginRequest(userName,password))))
                .andDo(print())
                .andExpect(status().isNotFound());
    }
    @Test
    @DisplayName("로그인 실패 - password 틀림")
    @WithMockUser
    void login_fail2() throws Exception{
        String userName="daehwan";
        String password="eoghks";

        when(userService.login(any(),any()))
                .thenThrow(new AppException(ErrorCode.INVALID_PASSWORD,""));

        mockMvc.perform(post("/api/v1/users/login")
                        .with(csrf()) //spring security test repository 추가해야함
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(new UserLoginRequest(userName,password))))
                .andDo(print())
                .andExpect(status().isUnauthorized());
    }
}