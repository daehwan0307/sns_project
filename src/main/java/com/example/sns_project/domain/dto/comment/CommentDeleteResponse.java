package com.example.sns_project.domain.dto.comment;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class CommentDeleteResponse {
    private String message;
    private Long commentId;
}
