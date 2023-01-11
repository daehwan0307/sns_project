package com.example.sns_project.repository;

import com.example.sns_project.domain.entity.Good;
import com.example.sns_project.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GoodRepository extends JpaRepository<Good,Long> {
    Boolean findByUser(User user);

    Integer countByPostId(Long postId);
}
