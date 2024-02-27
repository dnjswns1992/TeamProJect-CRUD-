package com.example.demo.repository;

import com.example.demo.entity.FormUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FormUserRepository extends JpaRepository<FormUserEntity, Integer> {
    boolean existsByEmailAndUsername(String email, String username);
    boolean existsByNickname(String nickname);

    Optional<FormUserEntity> findByUsername(String username);
    Optional<FormUserEntity> findByEmail(String email);
}
