package com.jay.vo.base;

/**
 * 错误信息封装
 * Created by hetiewei on 2017/2/28.
 */
public class ErrorMsg {
    //错误码
    private String code;
    //错误信息
    private String msg;

    public ErrorMsg() {
    }

    public ErrorMsg(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
