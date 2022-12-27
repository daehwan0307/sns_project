package com.example.sns_project.repository;

import com.example.sns_project.domain.dto.post.PostContentResponse;
import com.example.sns_project.domain.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post,Long> {


}
