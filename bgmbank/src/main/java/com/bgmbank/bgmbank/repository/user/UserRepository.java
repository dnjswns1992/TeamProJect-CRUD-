package com.bgmbank.bgmbank.repository.user;

import com.bgmbank.bgmbank.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);
    User findByNickname(String email);
    User findByPwd(String pwd);
}
