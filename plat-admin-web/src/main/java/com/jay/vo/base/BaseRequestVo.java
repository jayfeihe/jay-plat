package com.jay.vo.base;

/**
 * 对外接口，入参统一封装
 *
 * Created by hetiewei on 2017/3/1.
 */
public class BaseRequestVo<T> {
    //时间戳，默认当前时间戳(秒)
    private Long timeStamp;
    //签名
    private String sign;
    //请求数据
    private T data;

    public Long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
