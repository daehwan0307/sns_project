package com.example.sns_project.service;

import com.example.sns_project.domain.dto.post.PostContentResponse;
import com.example.sns_project.domain.dto.post.PostListResponse;
import com.example.sns_project.domain.dto.post.PostRequest;
import com.example.sns_project.domain.dto.post.PostResponse;
import com.example.sns_project.domain.entity.Post;
import com.example.sns_project.domain.entity.User;
import com.example.sns_project.exception.AppException;
import com.example.sns_project.exception.ErrorCode;
import com.example.sns_project.repository.PostRepository;
import com.example.sns_project.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.config.ResourceReaderRepositoryPopulatorBeanDefinitionParser;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    public PostResponse addPost(String title,String body,String userName) {

        User user = userRepository.findByUserName(userName)
                .orElseThrow(()-> new AppException(ErrorCode.USERNAME_NOT_FOUND, userName + "이 없습니다."));

        Post post = Post
                .builder()
                .title(title)
                .body(body)
                .user(user)
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
    public Page<PostContentResponse> getAllPost(Pageable pageable){

       Page<Post> posts = postRepository.findAll(pageable);
        Page<PostContentResponse> postContentResponses = posts.map(post -> PostContentResponse.builder()
                .id(post.getId())
                .title(post.getTitle())
                .body(post.getBody())
                .userName(post.getUser().getUserName())
                .lastModifiedAt(post.getLastModifiedAt())
                .createdAt(post.getCreatedAt())
                .build());



        return postContentResponses;

    }
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
    public PostResponse editPostById(Long postsId , PostRequest dto) {

        Post post = postRepository.findById(postsId)
                .orElseThrow(()-> new AppException(ErrorCode.POST_NOT_FOUND, postsId + "가 없습니다."));

        post.setBody(dto.getBody());
        post.setTitle(dto.getTitle());
        postRepository.save(post);
        PostResponse postResponse = PostResponse
                .builder()
                .message("포스트 수정 완료")
                .postId(post.getId())
                .build();

        return postResponse;
    }

}
