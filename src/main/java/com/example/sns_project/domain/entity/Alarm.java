package com.example.sns_project.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@Getter
@NoArgsConstructor
@Builder
@Entity
public class Alarm  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private String fromUserName;
    private String massage;
    private boolean readOrNot;

    public boolean getReadOrNot() {

        return readOrNot;
    }
    public void setReadOrNot() {

        this.readOrNot = true;
    }
}

