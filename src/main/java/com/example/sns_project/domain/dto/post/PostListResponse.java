package com.example.sns_project.domain.dto.post;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Builder
@Getter
@AllArgsConstructor

public class PostListResponse {
    private List<PostContentResponse> content;
    private Pageable pageable;
}
