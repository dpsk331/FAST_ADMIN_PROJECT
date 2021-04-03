package com.example.study.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString(exclude = {"user", "item"}) // 상호 참조 해제
public class OrderDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime orderAt;

    // OrderDetail → N : 1 ← User, 현재 객체를 기준으로 함!
    @ManyToOne // 연관관계 설정 시 상대편 객체에도 해당 내용을 추가해야 함!
    private User user; // user_id

    // N : 1
    @ManyToOne
    private Item item;

}
