package com.example.sns_project.controller;

import com.example.sns_project.domain.dto.Response;
import com.example.sns_project.domain.dto.post.PostContentResponse;
import com.example.sns_project.domain.dto.post.PostRequest;
import com.example.sns_project.domain.dto.post.PostResponse;
import com.example.sns_project.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    //게시글 추가
    @PostMapping
    public ResponseEntity<Response> addPost(@RequestBody PostRequest dto){
        PostResponse postResponse = postService.addPost(dto.getTitle(), dto.getBody());
        return ResponseEntity.ok().body(Response.success(postResponse));

    }

    //게시글 1개 조회
    @GetMapping("/{postId}")
    public ResponseEntity<Response> getPost(@PathVariable Long postId){

        PostContentResponse postContentResponse = postService.getPostById(postId);
        return ResponseEntity.ok().body(Response.success(postContentResponse));

    }

    //게시글 삭제
    @DeleteMapping("/{postId}")
    public ResponseEntity<Response> deletePost(@PathVariable Long postId){

        PostResponse postResponse = postService.deletePostById(postId);
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


