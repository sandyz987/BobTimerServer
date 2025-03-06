package com.sandyz.bobtimerserver.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum BobExceptionEnum {
    ERROR_PASSWORD(400, "用户名或密码错误"),
    USER_ALREADY_EXISTED(400, "用户名已存在"),
    REGISTER_USER_ERROR(400, "注册用户失败"),
    USER_NOT_EXISTED_ERROR(400, "用户名不存在"),


    ;
    private int code;
    private String msg;
}
