package com.example.study.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Integer price;

    private String content;


    // 1 : N

    // ▼ FetchType -> LAZY = 지연로딩, EAGER = 즉시로딩 ▼

    // LAZY -> 변수에 대해서 GET 메서드를 호출하지 않는 이상 연관관계에 대해서 호출하지 않겠다는 의미
    // SELECT * FROM ITEM WHERE ID = ?

    // EAGER -> 성능 저하 등의 위험, 추천X, 1:1, ManyToOne에 대해서 사용
    // 즉시 모든 것을 로딩을 하겠다는 의미, 연관관계가 있는 모든 테이블에 대해서 JOIN 발생
    // item_id = order_detail.item_id
    // user_id = order_detail.user_id
    // Join : item item0_ left outer join order_detail orderdetai1_ on item0_.id=orderdetai1_.item_id left outer join user user2_ on orderdetai1_.user_id=user2_.id
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "item")
    private List<OrderDetail> orderDetailList;

}
