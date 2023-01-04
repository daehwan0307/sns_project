package com.example.sns_project.controller;

import com.example.sns_project.domain.dto.Response;
import com.example.sns_project.domain.dto.comment.CommentRequest;
import com.example.sns_project.domain.dto.comment.CommentResponse;
import com.example.sns_project.domain.dto.post.PostRequest;
import com.example.sns_project.domain.dto.post.PostResponse;
import com.example.sns_project.service.CommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/posts")
@RequiredArgsConstructor
@Api(tags = {"Comment API"})
public class CommentController {

    private final CommentService commentService;
    @PostMapping("/{postsId}/comments")
    @ApiOperation(value = "댓글 작성",notes = "Comment를 이용하여 댓글을 작성합니다.")
       public ResponseEntity<Response> addComment(@RequestBody CommentRequest dto, Authentication authentication,@PathVariable Long postsId){
        CommentResponse commentResponse = commentService.addComment(dto,authentication.getName(), postsId);

        return ResponseEntity.ok().body(Response.success(commentResponse));
    }


}
