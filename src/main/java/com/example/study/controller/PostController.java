package com.example.study.controller;

import com.example.study.model.SearchParam;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api") // 클래스에 대해서 RequestMapping 시킨 것은 두 가지가 겹치더라도 스프링부트가 실행되는 것에 문제가 되지 않음
public class PostController {

    // POST 발생 경우 :
    // HTML <Form> 태그 사용 시
    // ajax 비동기화를 할 때 검색

    // POST란? http post body 안에 data를 넣어 보내겠다는 것!
    // 데이터 포맷 : JSON, XML, multipart-part / text-plain

    // @RequestMapping(method = RequestMethod.POST, path = "/postMethod") // 아래의 PostMapping과 동일한 구문
    @PostMapping("/postMethod") // , produces = {"appliation-json"}) 이와 같이 포맷 방식을 어떠한 방식으로 받을 것인지 지정, but 기본 형태인 JSON은 생략 가능
    public SearchParam postMethod(@RequestBody SearchParam searchParam) { // RequestBody에 SearchParam searchParam의 값들을 매칭시켜서 보내달라는 것
        return searchParam;
    }

}
