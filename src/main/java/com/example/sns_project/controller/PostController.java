package com.example.sns_project.controller;

import com.example.sns_project.domain.dto.Response;
import com.example.sns_project.domain.dto.post.PostContentResponse;
import com.example.sns_project.domain.dto.post.PostRequest;
import com.example.sns_project.domain.dto.post.PostResponse;
import com.example.sns_project.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    //게시글 추가
    @PostMapping
    public ResponseEntity<Response> addPost(@RequestBody PostRequest dto, Authentication authentication){
        PostResponse postResponse = postService.addPost(dto.getTitle(), dto.getBody(),authentication.getName());
        return ResponseEntity.ok().body(Response.success(postResponse));

    }

    //게시글 1개 조회
    @GetMapping("/{id}")
    public ResponseEntity<Response> getPost(@PathVariable Long id){

        PostContentResponse postContentResponse = postService.getPostById(id);
        return ResponseEntity.ok().body(Response.success(postContentResponse));

    }

    //게시글 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Response> deletePost(@PathVariable Long id){

        PostResponse postResponse = postService.deletePostById(id);
        return ResponseEntity.ok().body(Response.success(postResponse));

    }
//    @PutMapping("/{id}")
//    public ResponseEntity<Response> editPost(@PathVariable Long id,@RequestBody PostRequest dto){
//
//        PostResponse postResponse = postService.editPostById(id);
//        return ResponseEntity.ok().body(Response.success(postResponse));
//
//    }



}


