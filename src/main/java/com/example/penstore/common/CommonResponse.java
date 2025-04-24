package com.example.penstore.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommonResponse<T> implements Serializable {
    private int status;
    private String message;
    private T data;

    private CommonResponse(int status) {
        this.status = status;
    }

    private CommonResponse(int status, String message) {
        this.status = status;
        this.message = message;
    }

    private CommonResponse(int status, T data) {
        this.status = status;
        this.data = data;
    }

    private CommonResponse(int status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public static <T> CommonResponse<T> createForSuccess() {
        return new CommonResponse<T>(ResponseCode.SUCCESS.getCode());
    }
    public static <T> CommonResponse<T> createForSuccess(T data) {
        return new CommonResponse<T>(ResponseCode.SUCCESS.getCode(), data);
    }
//    public static <T> CommonResponse<T> createForSuccess(String message) {
//        return new CommonResponse<T>(ResponseCode.SUCCESS.getCode(), message);
//    }
    public static <T> CommonResponse<T> createForError(int code, String message) {
        return new CommonResponse<T>(code, message);
    }
    @JsonIgnore
    public boolean isSuccess() {
        return this.status == ResponseCode.SUCCESS.getCode();
    }

}
