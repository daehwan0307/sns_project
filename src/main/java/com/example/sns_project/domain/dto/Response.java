package com.example.sns_project.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Builder
public class Response<T>{
    private String resultCode;
    private T result;

    public static <T> Response<T> error(String resultCode, T result) {
        return new Response(resultCode, result);
    }

    public static <T> Response<T> success(T result) {
        return new Response("SUCCESS", result);
    }
    public static Response<Void> success() {
        return new Response("SUCCESS", null);
    }

}
