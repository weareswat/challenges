package com.mixfar.posts.utils.enums;

public enum ErrorCode {
    OK(200, "OK"),
    CREATED(201, "CREATED"),
    DELETED(201, "DELETED"),
    UPDATED(200, "UPDATED"),
    INVALID_PARAMETERS(400, "Bad Request"),
    UNAUTHORIZED(401, "Unauthorized"),
    FORBIDDEN(403, "Forbidden"),
    NOT_FOUND(404, "Not Found"),
    FATAL(500, "Internal Server Error");

    private Integer code;
    private String reason;

    ErrorCode(Integer code, String reason) {
        this.code = code;
        this.reason = reason;
    }

    public Integer getCode() {
        return this.code;
    }

    public String getReason() {
        return reason;
    }

}
