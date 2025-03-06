package com.sandyz.bobtimerserver.exception;

import com.sandyz.bobtimerserver.util.ResultMessage;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@ResponseBody
public class BobExceptionHandler {


    @ExceptionHandler(BobException.class)
    public ResultMessage handleException(BobException e) {
        BobExceptionEnum em = e.getExceptionEnum();
        return ResultMessage.fail(em.getCode(), em.getMsg());
    }
}
