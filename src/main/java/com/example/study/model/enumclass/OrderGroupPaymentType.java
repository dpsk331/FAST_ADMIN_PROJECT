package com.example.study.model.enumclass;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OrderGroupPaymentType {
    CARD(0, "카드", "카드로 결제"),
    CASH(1, "현금", "현금으로 결제"),
    POINT(2, "포인트", "포인트로 결제")
            ;

    private Integer id;
    private String title;
    private String description;
}
