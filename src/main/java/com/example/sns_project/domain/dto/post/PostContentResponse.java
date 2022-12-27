package com.example.sns_project.domain.dto.post;

import com.example.sns_project.domain.entity.AuditEntity;
import com.example.sns_project.domain.entity.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class PostContentResponse extends AuditEntity {

    private Long id;
    private String title;
    private String body;
    private String userName;

    private LocalDateTime createdAt;
    private LocalDateTime lastModifiedAt;


}
