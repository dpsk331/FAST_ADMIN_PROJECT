package com.example.study.model.enumclass;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OrderGroupOrderType {

    ALL(0, "일괄 발송", "모든 상품을 일괄 발송"),
    EACH(1, "개별 발송", "모든 상품을 준비되는대로 개별 발송")
    ;

    private Integer id;
    private String title;
    private String description;

}
