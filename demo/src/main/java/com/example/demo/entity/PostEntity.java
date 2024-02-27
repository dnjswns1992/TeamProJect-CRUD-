package com.example.demo.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

import java.time.LocalDate;
import java.util.List;

@Entity
public class PostEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer postId;
    private String username;
    private String title;
    private String content;
    private LocalDate writtenTime;
    private int reportCount;
    private byte[] picture;
    private int likeCount;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "common_user_id")
    private CommonUserEntity commonUserEntity;

    @OneToMany(mappedBy = "postEntity",cascade = CascadeType.ALL)
    private List<CommentEntity> comments;
}
