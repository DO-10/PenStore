package com.example.penstore.common;

import lombok.Getter;

import java.io.Serializable;
@Getter
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
    public static <T> CommonResponse<T> createForSuccess(String message) {
        return new CommonResponse<T>(ResponseCode.SUCCESS.getCode(), message);
    }
    public static <T> CommonResponse<T> createForError(String message) {
        return new CommonResponse<T>(ResponseCode.ERROR.getCode(), message);
    }
    public boolean isSuccess() {
        return this.status == ResponseCode.SUCCESS.getCode();
    }

}
