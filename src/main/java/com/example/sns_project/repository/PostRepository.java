package com.example.sns_project.repository;

import com.example.sns_project.domain.dto.post.PostContentResponse;
import com.example.sns_project.domain.entity.Post;
import com.example.sns_project.domain.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostRepository extends JpaRepository<Post,Long> {

    Page<Post> findAllByUser(User user, Pageable pageable);

}
