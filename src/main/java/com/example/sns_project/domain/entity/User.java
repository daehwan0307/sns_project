package com.example.sns_project.domain.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Setter
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String user_name;
    private String password;
    private int role;
    private LocalDateTime registered_at;
    private LocalDateTime removed_at;
    private LocalDateTime updated_at;



}
