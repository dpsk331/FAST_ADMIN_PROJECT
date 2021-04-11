package com.example.study.model.enumclass;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OrderGroupStatus {
    COMPLETE(0, "주문 완료", "주문완료 상태"),
    INCOMPLETE(2, "주문 중", "주문 중 상태")
    ;

    private Integer id;
    private String title;
    private String description;
}
