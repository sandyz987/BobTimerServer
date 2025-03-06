package com.sandyz.bobtimerserver.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class BobException extends RuntimeException{
    private BobExceptionEnum exceptionEnum;
}
