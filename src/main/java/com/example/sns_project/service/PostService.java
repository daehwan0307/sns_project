package com.example.sns_project.service;

import com.example.sns_project.domain.dto.post.PostContentResponse;
import com.example.sns_project.domain.dto.post.PostResponse;
import com.example.sns_project.domain.entity.Post;
import com.example.sns_project.exception.AppException;
import com.example.sns_project.exception.ErrorCode;
import com.example.sns_project.repository.PostRepository;
import com.example.sns_project.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    public PostResponse addPost(String title,String body) {

        Post post = Post
                .builder()
                .title(title)
                .body(body)
                .build();
        postRepository.save(post);
        PostResponse postResponse = PostResponse
                .builder()
                .message("포스트 등록 완료")
                .postId(post.getId())
                .build();

        return postResponse;
    }
    public PostResponse deletePostById(Long postsId) {

        Post post = postRepository.findById(postsId)
                .orElseThrow(()-> new AppException(ErrorCode.POST_NOT_FOUND, postsId + "가 없습니다."));
        postRepository.delete(post);
        PostResponse postResponse = PostResponse
                .builder()
                .message("포스트 삭제 완료")
                .postId(post.getId())
                .build();

        return postResponse;
    }
//    public PostResponse editPostById(Long postsId) {
//
//        Post post = postRepository.findById(postsId)
//                .orElseThrow(()-> new AppException(ErrorCode.POST_NOT_FOUND, postsId + "가 없습니다."));
//        postRepository.update(post);
//        PostResponse postResponse = PostResponse
//                .builder()
//                .message("포스트 수정 완료")
//                .postId(post.getId())
//                .build();
//
//        return postResponse;
//    }
    public PostContentResponse getPostById(Long postsId) {
        Post post = postRepository.findById(postsId)
                .orElseThrow(()-> new AppException(ErrorCode.POST_NOT_FOUND, postsId + "가 없습니다."));

        PostContentResponse postContentResponse = PostContentResponse
                    .builder()
                    .id(postsId)
                    .title(post.getTitle())
                    .body(post.getBody())
                    .lastModifiedAt(post.getLastModifiedAt())
                    .createdAt(post.getCreatedAt())
                    .userName(post.getUser().getUserName())
                    .build();

        return postContentResponse;
    }

}
