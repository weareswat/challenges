package com.mixfar.posts.utils.response;

import com.mixfar.posts.utils.enums.ErrorCode;

public class DataResponse<T> extends Response {
    private T data;

    public DataResponse() {
    }

    public DataResponse(ErrorCode errorCode) {
        super(errorCode);
    }

    public DataResponse(ErrorCode errorCode, T data) {
        super(errorCode);
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
