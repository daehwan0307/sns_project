package com.example.sns_project.service;

import com.example.sns_project.domain.dto.post.PostResponse;
import com.example.sns_project.domain.entity.Post;
import com.example.sns_project.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
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
}
