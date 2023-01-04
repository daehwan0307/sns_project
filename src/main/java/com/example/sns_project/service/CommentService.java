package com.example.sns_project.service;

import com.example.sns_project.domain.dto.comment.CommentRequest;
import com.example.sns_project.domain.dto.comment.CommentResponse;
import com.example.sns_project.domain.dto.post.PostResponse;
import com.example.sns_project.domain.entity.Comment;
import com.example.sns_project.domain.entity.Post;
import com.example.sns_project.domain.entity.User;
import com.example.sns_project.exception.AppException;
import com.example.sns_project.exception.ErrorCode;
import com.example.sns_project.repository.CommentRepository;
import com.example.sns_project.repository.PostRepository;
import com.example.sns_project.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;

    public CommentResponse addComment(CommentRequest dto,String userName,Long postId){
        User user = userRepository.findByUserName(userName)
                .orElseThrow(()-> new AppException(ErrorCode.USERNAME_NOT_FOUND, userName + "이 없습니다."));

        Post post = postRepository.findById(postId)
                .orElseThrow(()-> new AppException(ErrorCode.POST_NOT_FOUND,"post가 존재하지 않습니다."));

        Comment comment = Comment.builder()
                .comment(dto.getComment())
                .user(user)
                .post(post)
                .build();
        commentRepository.save(comment);

        CommentResponse commentResponse = CommentResponse.builder()
                .id(comment.getId())
                .comment(comment.getComment())
                .userName(user.getUserName())
                .postId(post.getId())
                .createdAt(comment.getCreatedAt())
                .lastModifiedAt(comment.getLastModifiedAt())
                .build();


        return commentResponse;

    }


}
