package com.example.penstore.common;

import lombok.Getter;

@Getter
public enum ResponseCode {
    SUCCESS(200, "Success"),
    ERROR(500, "Error"),
    NEED_LOGIN(401, "Need Login"),
    ILLEGAL_ARGUMENT(400, "Illegal Argument");
    private final int code;
    private final String description;
    ResponseCode(int code, String description) {
        this.code = code;
        this.description = description;
    }
}
