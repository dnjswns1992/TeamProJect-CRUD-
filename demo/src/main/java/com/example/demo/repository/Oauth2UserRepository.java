package com.example.demo.repository;

import com.example.demo.entity.Oauth2UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Oauth2UserRepository extends JpaRepository<Oauth2UserEntity, Integer> {
}
