package com.mixfar.posts.utils.response;

import com.mixfar.posts.utils.enums.ErrorCode;

import java.io.Serializable;

public class Response implements Serializable {
    private Integer code;
    private String message;

    public Response() {
    }

    public Response(ErrorCode errorCode){
        this.code = errorCode.getCode();
        this.message = errorCode.getReason();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
