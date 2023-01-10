package com.example.sns_project.service;

import com.example.sns_project.domain.entity.Good;
import com.example.sns_project.domain.entity.Post;
import com.example.sns_project.domain.entity.User;
import com.example.sns_project.exception.AppException;
import com.example.sns_project.exception.ErrorCode;
import com.example.sns_project.repository.GoodRepository;
import com.example.sns_project.repository.PostRepository;
import com.example.sns_project.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GoodService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    private final GoodRepository goodRepository;

    public String clickLike(String userName, Long postId){
        User user = userRepository.findByUserName(userName)
                .orElseThrow(()-> new AppException(ErrorCode.USERNAME_NOT_FOUND, userName + "이 없습니다."));

        Post post = postRepository.findById(postId)
                .orElseThrow(()-> new AppException(ErrorCode.POST_NOT_FOUND,"post가 존재하지 않습니다."));

        Good good = Good.builder()
                .post(post)
                .user(user)
                .build();
        
        goodRepository.save(good);

        return "좋아요를 눌렀습니다.";

    }
    public Long likeCount(Long postId){
        return goodRepository.countByPostId(postId);
    }
}
