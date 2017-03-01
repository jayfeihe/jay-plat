package com.jay.vo.base;


/**
 * Created by hetiewei on 2017/2/28.
 */
public class ResponseVo<T> {
    private boolean status;
    private String message;
    private ErrorMsg msg;
    private T data;

    public ResponseVo(boolean status) {
        this.status = status;
    }

    public ResponseVo(boolean status, T data) {
        this.status = status;
        this.data = data;
    }

    public ResponseVo(boolean status, ErrorMsg msg) {
        this.status = status;
        this.msg = msg;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ErrorMsg getMsg() {
        return msg;
    }

    public void setMsg(ErrorMsg msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
