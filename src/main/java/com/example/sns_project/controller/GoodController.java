package com.example.sns_project.controller;

import com.example.sns_project.domain.dto.Response;
import com.example.sns_project.service.GoodService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/posts")
@RequiredArgsConstructor
@Api(tags = {"Like API"})
public class GoodController {

    private final GoodService likeService;

    @ApiOperation(value = "좋아요", notes = "클릭하면 좋아요가 눌러집니다.")
    @PostMapping("/{postId}/likes")
    public Response<String> clickLike(@PathVariable Long postId, Authentication authentication){

        return Response.success(likeService.clickLike(authentication.getName(),postId));

    }
    @GetMapping("/{postsId}/likes")
    @ApiOperation(value = "좋아요수", notes = "게시글의 좋아요 개수 조회")
    public Response<Integer> getLikes(@PathVariable Long postsId) {
        return Response.success(likeService.likeCount(postsId));
    }

}
