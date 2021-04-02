package com.example.study.model;

// 스프링부트에서 jackson 라이브러리를 통해 JSON 형태인 {"account" : "","email" : "","page" :0} 와 같이 변경하려 반환!

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor // 모든 매개변수를 가지는 생성자 추가 애노테이션
public class SearchParam {

    private String account;
    private String email;
    private int page;

}
