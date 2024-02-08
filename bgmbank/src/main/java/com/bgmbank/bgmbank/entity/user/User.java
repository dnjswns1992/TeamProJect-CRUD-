package com.bgmbank.bgmbank.entity.user;

import com.bgmbank.bgmbank.entity.comment.Comment;
import com.bgmbank.bgmbank.entity.post.Post;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nickname;
    private String email;
    private String pwd;
    private String role;
    private String tier;
    private Long point;
    private String profileImgUrl;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Post> postList;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Comment> commentList;
}
