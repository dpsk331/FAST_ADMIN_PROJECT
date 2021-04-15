package com.example.study.model.network;

import com.example.study.model.network.response.ItemApiResponse;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Header<T> {

    // api 통신 시간
    //@JsonProperty("transaction_time") // json 형태가 만들어질 때 해당 형태로 나타남, 하지만 일일이 변경하기엔 번거로움!
    private LocalDateTime transactionTime;

    // api 응답 코드
    private  String resultCode;

    // api 부가 설명
    private String description;

    // 항상 변화하는 데이터 부분(body)는 제너릭 타입으로 선언
    private T data;

    private Pagination pagination;

    // 정상적인 호출 OK
    public static <T> Header<T> OK() {
        return (Header<T>)Header.builder()
                .transactionTime(LocalDateTime.now())
                .resultCode("OK")
                .description("OK")
                .build();
    }

    // DATA OK
    public static <T> Header<T> OK(T data) {
        return (Header<T>)Header.builder()
                .transactionTime(LocalDateTime.now())
                .resultCode("OK")
                .description("OK")
                .data(data)
                .build();
    }

    // DATA OK
    public static <T> Header<T> OK(T data, Pagination pagination) {
        return (Header<T>)Header.builder()
                .transactionTime(LocalDateTime.now())
                .resultCode("OK")
                .description("OK")
                .data(data)
                .pagination(pagination)
                .build();
    }

    // ERROR
    public static <T> Header<T> ERROR(String description) {
        return (Header<T>)Header.builder()
                .transactionTime(LocalDateTime.now())
                .resultCode("ERROR")
                .description(description)
                .build();
    }

}
