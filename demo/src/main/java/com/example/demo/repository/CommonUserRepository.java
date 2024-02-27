package com.example.demo.repository;

import com.example.demo.entity.CommonUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommonUserRepository extends JpaRepository<CommonUserEntity, Integer> {
}
