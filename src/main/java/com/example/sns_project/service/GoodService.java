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

        if(goodRepository.findByUserAndPost(user,post).isEmpty()){

            goodRepository.save(good);
            return "좋아요를 눌렀습니다.";
        }else {
            throw new AppException(ErrorCode.ALREADY_CLICK_LIKE,"이미 좋아요를 눌렀습니다.");
        }

    }
    public Integer likeCount(Long postId){

        return goodRepository.countByPostId(postId);
    }
}
