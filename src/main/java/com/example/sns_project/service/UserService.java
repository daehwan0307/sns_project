package com.example.sns_project.service;

import com.example.sns_project.domain.entity.User;

import com.example.sns_project.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public String join(String userName, String password){
        //userName 중복  check

        userRepository.findByUserName(userName)
                .ifPresent(user->{
                    throw  new RuntimeException(userName+": 존재합니다");
                });
        //저장
        User user = User.builder()
                .userName(userName)
                .password(password)
                .build();
        userRepository.save(user);

        return "SUCCESS";
    }


}
