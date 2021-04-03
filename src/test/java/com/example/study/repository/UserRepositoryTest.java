package com.example.study.repository;

import com.example.study.StudyApplicationTests;
import com.example.study.model.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

public class UserRepositoryTest extends StudyApplicationTests {

    // ▼ Dependency Injection(DI) : 의존성 주입
    // - 객체를 직접 만들지 않고, 스프링이 객체 직접 관리 및 의존성 주입
    // - 스프링의 가장 큰 장점 중 하나
    // - 디자인 패턴
    // - 싱글턴 패턴
    @Autowired
    private UserRepository userRepository;

    @Test
    public void create() {
        // JPA의 장점은 Object를 가지고 데이터베이스를 관리할 수 있도록 도와주는 툴
        // String sql = insert into user (%s, %s, $d) value (account, email, age); XXX

        User user = new User(); // DI X

        // user.setId(); // DB에서 AI 이기 때문에 필요X
        user.setAccount("TestUser02");
        user.setEmail("TestUser02@gmail.com");
        user.setPhoneNumber("010-1111-2222");
        user.setCreateAt(LocalDateTime.now());
        user.setCreateBy("TestUser02");

        User newUser = userRepository.save(user);
        System.out.println("newUser : " + newUser);
    }

    public void read() {

    }

    public void update() {

    }
    public void delete() {

    }
}
