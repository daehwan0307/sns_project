package com.example.sns_project.service;

import com.example.sns_project.domain.entity.User;

import com.example.sns_project.exception.AppException;
import com.example.sns_project.exception.ErrorCode;
import com.example.sns_project.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;

    public String join(String userName, String password){
        //userName 중복  check

        userRepository.findByUserName(userName)
                .ifPresent(user->{
                    throw  new AppException(ErrorCode.DUPLICATED_USER_NAME, userName+"는 이미 있습니다.");
                });
        //저장
        User user = User.builder()
                .userName(userName)
                .password(encoder.encode(password))
                .build();
        userRepository.save(user);

        return "SUCCESS";
    }


}
