package com.example.sns_project.domain.dto.comment;

import com.example.sns_project.domain.entity.Comment;
import com.example.sns_project.domain.entity.Post;
import com.example.sns_project.domain.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CommentRequest {
    private String comment;

    public Comment toEntity(User user, Post post) {
        return Comment.builder()
                .comment(this.comment)
                .post(post)
                .user(user)
                .build();
    }

}
