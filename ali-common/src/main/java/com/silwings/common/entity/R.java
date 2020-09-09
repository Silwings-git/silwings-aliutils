package com.silwings.common.entity;

import java.io.Serializable;

public class R<T> implements Serializable {

    /**
     * 状态码
     */
    private Integer code;
    /**
     * 返回消息
     */
    private String msg;
    /**
     * 数据内容
     */
    private T data;

    public R setCode(Integer code) {
        this.code = code;
        return this;
    }


    public String getMsg() {
        return msg;
    }

    public R setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public T getData() {
        return data;
    }

    public R setData(T data) {
        this.data = data;
        return this;
    }

    private static final String DEFAULT_SUCCESS_MESSAGE = "SUCCESS";

    public static R success() {
        return new R()
                .setCode(Rcode.SUCCESS.code)
                .setMsg(DEFAULT_SUCCESS_MESSAGE);
    }

    public static R success(Object data) {
        return new R()
                .setCode(Rcode.SUCCESS.code)
                .setMsg(DEFAULT_SUCCESS_MESSAGE)
                .setData(data);
    }


    public static R fail(Integer code,String message) {
        return new R()
                .setCode(code)
                .setMsg(message);
    }

    public static R fail(String message) {
        return new R()
                .setCode(Rcode.FAIL.code)
                .setMsg(message);
    }

    public static R fail(String message, Object data) {
        return new R()
                .setCode(Rcode.FAIL.code)
                .setMsg(message)
                .setData(data);
    }


    //未认证返回
    public static R unauthentication(String message, Object data) {
        return new R()
                .setCode(Rcode.UNAUTHENTICATION.code)
                .setMsg(message)
                .setData(data);
    }


    //未授权返回
    public static R unauthorized(String message, Object data) {
        return new R()
                .setCode(Rcode.UNAUTHORIZED.code)
                .setMsg(message)
                .setData(data);
    }


    public Integer getCode() {
        return code;
    }


    public static String getDefaultSuccessMessage() {
        return DEFAULT_SUCCESS_MESSAGE;
    }

    public R() {
    }

}
