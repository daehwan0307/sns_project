package com.example.sns_project.repository;

import com.example.sns_project.domain.entity.Alarm;
import com.example.sns_project.domain.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface AlarmRepository extends JpaRepository<Alarm, Long> {
    Page<Alarm> findByUser(User user, Pageable pageable);

//    Optional<Alarm> findByTargetIdAndFromUserId(Long postId, Long userId);
}
