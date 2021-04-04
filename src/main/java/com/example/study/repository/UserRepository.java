package com.example.study.repository;

import com.example.study.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> { // JpaRepository를 상속 받게 되면 기본적인 CRUD 제공

    // Query Method
    // select * from user where account = ? << test03, test04
    Optional<User> findByAccount(String account); // 변수명은 상관X, 앞에 findBy 뒤에 오는 컬럼명이 중요!

    Optional<User> findByEmail(String email);

    // select * from user where account = ? and email = ?
    Optional<User> findByAccountAndEmail(String account, String email);

}
