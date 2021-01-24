package com.mixfar.posts.utils.response;

import com.mixfar.posts.utils.enums.ErrorCode;

public class ErrorResponse<T> extends Response{
    private T error;

    public ErrorResponse() {
    }

    public ErrorResponse(ErrorCode errorCode) {
        super(errorCode);
    }

    public ErrorResponse(ErrorCode errorCode, T error) {
        super(errorCode);
        this.error = error;
    }

    public T getError() {
        return error;
    }

    public void setError(T error) {
        this.error = error;
    }
}
