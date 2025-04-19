package com.example.penstore.common;

import lombok.Getter;

@Getter
public enum ResponseCode {
    SUCCESS(200, "Success"),
    ERROR(500, "Error"),
    NEED_LOGIN(4011, "Need Login"),
    ILLEGAL_ARGUMENT(400, "Illegal Argument"),
    UNAUTHORIZED(401, "Unauthorized未授权"),
    FORBIDDEN(403, "Forbidden禁止访问"),
    NOT_FOUND(404, "Not Found资源未找到"),
    TOO_MANY_REQUESTS(429, "Too Many Requests请求过于频繁"),
    INTERNAL_ERROR(500, "Internal Server Error服务器错误"),

    /* ============== 业务状态码 ============== */
    STOCK_NOT_ENOUGH(60001, "Stock Not Enough库存不足"),
    PAYMENT_FAILED(60004, "Payment Failed支付失败");

    private final int code;
    private final String description;
    ResponseCode(int code, String description) {
        this.code = code;
        this.description = description;
    }
}
