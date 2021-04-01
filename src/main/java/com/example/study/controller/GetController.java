package com.example.study.controller;

import com.example.study.model.SearchParam;
import org.springframework.web.bind.annotation.*;

// ▼ 컴파일러에게 이곳을 컨트롤러로 사용한다고 알리는 애노테이션
@RestController
// ▼ 이곳으로 들어올 API 주소를 매핑하기 위한 애노테이션, 괄호 안에는 어떠한 주소를 받을지..
@RequestMapping("/api") // Localhost:8080/api
public class GetController {

    @RequestMapping(method = RequestMethod.GET, path = "/getMethod") // Localhost:8080/api/getMethod
    public String getRequest() {
        return "Hi getMethod";
    }

    // ▼ RequestMapping과 다르게 메소드를 지정하지 않고, 주소만 설정해주면 됨!
    @GetMapping("/getParameter") // Localhost:8080/api/getParameter?id=1234&password=abcd
    public String getParameter(@RequestParam String id, @RequestParam(name = "password") String pwd) {
        String passsword = "bbbb";

        System.out.println("id : " + id);
        System.out.println("password : " + pwd);

        return id + pwd;
    }

    // Localhost:8080/api/getMultiParameter?account=abcd&email=study@gmail.com&page=10
    @GetMapping("/getMultiParameter")
    public SearchParam getMultiParameter(SearchParam searchParam) {
        System.out.println(searchParam.getAccount());
        System.out.println(searchParam.getEmail());
        System.out.println(searchParam.getPage());

        // {"account" : "","email" : "","page" : 0}
        return searchParam;
    }

}
