package com.example.study.repository;

import com.example.study.StudyApplicationTests;
import com.example.study.model.entity.User;
import org.junit.jupiter.api.Assertions;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

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

        String account = "Test02";
        String password = "Test02";
        String status = "REGISTERED";
        String email = "Test02@gmail.com";
        String phoneNumber = "010-1111-2222";
        LocalDateTime registeredAt = LocalDateTime.now();
        LocalDateTime createdAt = LocalDateTime.now();
        String createdBy = "AdminServer";

        /*User user = new User();
        user.setAccount(account);
        user.setPassword(password);
        user.setStatus(status);
        user.setEmail(email);
        user.setPhoneNumber(phoneNumber);
        user.setRegisteredAt(registeredAt);*/

        // @Builder 사용
        User user = User.builder().
                    account(account).
                    password(password).
                    status(status).
                    email(email).
                    build();

        User newUser = userRepository.save(user);

        Assertions.assertNotNull(newUser);

    }

    @Test
    @Transactional
    public void read() {

        User user = userRepository.findFirstByPhoneNumberOrderByIdDesc("010-1111-1111");

        if(user != null) {
            user.getOrderGroupList().stream().forEach(orderGroup -> {
                System.out.println("-----------장바구니------------");
                System.out.println("수령인 : " + orderGroup.getRevName());
                System.out.println("수령지 : " + orderGroup.getRevAddress());
                System.out.println("총금액 : " + orderGroup.getTotalPrice());
                System.out.println("총수량 : " + orderGroup.getTotalQuantity());

                System.out.println("----------주문상세------------");
                orderGroup.getOrderDetailList().forEach(orderDetail -> {
                    System.out.println("파트너사 이름 : " + orderDetail.getItem().getPartner().getName());
                    System.out.println("파트너사 카테고리 : " + orderDetail.getItem().getPartner().getCategory().getTitle());
                    System.out.println("주문 상품 : " + orderDetail.getItem().getName());
                    System.out.println("고객센터 번호 : " + orderDetail.getItem().getPartner().getCallCenter());
                    System.out.println("주문의 상태 : " + orderDetail.getStatus());
                    System.out.println("도착예정일자 : " + orderDetail.getArrivalDate());
                });
            });
        }

        Assertions.assertNotNull(user);

    }

    @Test
    public void update() {
        Optional<User> user = userRepository.findById(2L);

        user.ifPresent(selectUser -> {
            selectUser.setAccount("PPPP");
            selectUser.setUpdatedAt(LocalDateTime.now());
            selectUser.setUpdatedBy("update method()");

            userRepository.save(selectUser);
        });
    }

    @Test
    @Transactional // 데이터베이스에서 동작X, 실행 후 Rollback함
    public void delete() {
        Optional<User> user = userRepository.findById(3L);

        Assertions.assertTrue(user.isPresent()); //true

        user.ifPresent(selectUser -> {
            userRepository.delete(selectUser);
        });

        Optional<User> deleteUser = userRepository.findById(3L);

        Assertions.assertFalse(deleteUser.isPresent()); // false
    }
}
