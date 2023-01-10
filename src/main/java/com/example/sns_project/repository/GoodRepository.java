package com.example.sns_project.repository;

import com.example.sns_project.domain.entity.Good;
import com.example.sns_project.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GoodRepository extends JpaRepository<Good,Long> {
    Boolean findByUser(User user);

    Long countByPostId(Long postId);
}
