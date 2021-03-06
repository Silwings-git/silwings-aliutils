package com.silwings.common.entity;

public enum Rcode {
    // 业务成功
    SUCCESS(100200),
    // 业务失败
    FAIL(100400),
    // 未认证
    UNAUTHENTICATION(100401),
    // 未授权
    UNAUTHORIZED(100402);

    public int code;

    Rcode(int code) {
        this.code = code;
    }
}
