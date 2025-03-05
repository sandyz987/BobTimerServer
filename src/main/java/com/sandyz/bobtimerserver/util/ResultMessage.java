package com.sandyz.bobtimerserver.util;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class ResultMessage {

    private int code;
    private String msg;
    private Object data;

    private ResultMessage() {}

    public static ResultMessage success() {
        return new ResultMessage();
    }

    public static ResultMessage success(int code, String msg, Object data) {
        return new ResultMessage().setCode(code).setMsg(msg).setData(data);
    }

    public static ResultMessage success(int code, String msg) {
        return new ResultMessage().setCode(code).setMsg(msg);
    }

    public static ResultMessage success(int code, Object data) {
        return new ResultMessage().setCode(code).setData(data);
    }

    public static ResultMessage fail() {
        return new ResultMessage();
    }

    public static ResultMessage fail(int code, String msg) {
        return new ResultMessage().setCode(code).setMsg(msg);
    }

    public ResultMessage setCode(int code) {
        this.code = code;
        return this;
    }

    public ResultMessage setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public ResultMessage setData(Object data) {
        this.data = data;
        return this;
    }
}