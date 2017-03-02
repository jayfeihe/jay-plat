package com.jay.vo.base;


/**
 * 对外接口，出参统一封装
 *
 * 正常：
 *      status=true，data是返回数据，error = null
 *
 * 错误：
 *      status=false，data = null, error返回错误信息
 *
 * message，用于返回可选信息
 *
 * Created by hetiewei on 2017/2/28.
 */
public class BaseResponseVo<T> {
    //接口返回状态，标记处理逻辑  true:成功 false:失败
    private boolean status;
    //说明
    private String message;
    //错误信息的封装(包含错误码和错误说明)
    private ErrorMsg error;
    //返回数据
    private T data;

    public BaseResponseVo(boolean status) {
        this.status = status;
    }

    public BaseResponseVo(boolean status, T data) {
        this.status = status;
        this.data = data;
    }

    public BaseResponseVo(boolean status, ErrorMsg error) {
        this.status = status;
        this.error = error;
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

    public ErrorMsg getError() {
        return error;
    }

    public void setError(ErrorMsg error) {
        this.error = error;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
