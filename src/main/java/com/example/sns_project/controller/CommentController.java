package com.example.sns_project.controller;

import com.example.sns_project.domain.dto.Response;
import com.example.sns_project.domain.dto.comment.CommentDeleteResponse;
import com.example.sns_project.domain.dto.comment.CommentRequest;
import com.example.sns_project.domain.dto.comment.CommentResponse;
import com.example.sns_project.domain.dto.post.PostRequest;
import com.example.sns_project.domain.dto.post.PostResponse;
import com.example.sns_project.service.CommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
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
    @ApiOperation(value = "댓글 작성",notes = "Comment를 이용하여 댓글을 작성합니다.")
    @PostMapping("/{postsId}/comments")

       public ResponseEntity<Response> addComment(@RequestBody CommentRequest dto, Authentication authentication,@PathVariable Long postsId){
        CommentResponse commentResponse = commentService.addComment(dto,authentication.getName(), postsId);

        return ResponseEntity.ok().body(Response.success(commentResponse));
    }

    @ApiOperation(value = "댓글 삭제", notes = "CommentId와 PostId를 이용하여 댓글을 삭제합니다.")
    @DeleteMapping("/{postsId}/comments/{id}")
    public ResponseEntity<Response> deletePost(@PathVariable Long id,Long postsId){

        CommentDeleteResponse commentDeleteResponse = commentService.deleteComment(postsId,id);
        return ResponseEntity.ok().body(Response.success(commentDeleteResponse));

    }

    @ApiOperation(value = "댓글 수성",notes = "Comment를 이용하여 댓글을 수정합니다.")
    @PutMapping("/{postsId}/comments/{id}")

    public ResponseEntity<Response> editComment(@RequestBody CommentRequest dto, Authentication authentication,@PathVariable Long postsId,Long id){
        CommentResponse commentResponse = commentService.editComment(dto, postsId,id);

        return ResponseEntity.ok().body(Response.success(commentResponse));
    }

    @ApiOperation(value = "댓글 전체 조회", notes = "댓글 전체 리스트를 조회합니다.")
    @GetMapping("/{postsId}/comments")
    public Response<Page<CommentResponse>> getAllComments(@PageableDefault(size = 20, sort = {"id"}, direction = Sort.Direction.DESC)Pageable pageable,@PathVariable Long postsId)
    {
        return Response.success(commentService.getAllComments(pageable,postsId));
    }

}
