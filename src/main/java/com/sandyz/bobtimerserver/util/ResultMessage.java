package com.sandyz.bobtimerserver.util;
import lombok.Data;

import java.util.Map;

@Data
public class ResultMessage {

    private int code;
    private String msg;
    private Object data;

    private ResultMessage() {}

    public static ResultMessage success() {
        return new ResultMessage().setCode(200);
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

    public static ResultMessage handle(Boolean isSuccess) {
        if (isSuccess) {
            return success();
        } else {
            return fail();
        }
    }

    public static ResultMessage idWrapper(int resultId) {
        if (resultId >= 0) {
            Map<String, Integer> map = Map.of("id", resultId);
            return success(200, map);
        } else {
            return fail();
        }
    }

    public static ResultMessage fail() {
        return new ResultMessage().setCode(400);
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