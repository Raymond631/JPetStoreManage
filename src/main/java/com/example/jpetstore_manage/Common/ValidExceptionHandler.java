package com.example.jpetstore_manage.Common;

import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author Raymond Li
 * @create 2023-03-20 18:48
 * @description 参数校验异常处理器
 */
@RestControllerAdvice
public class ValidExceptionHandler {
    @ExceptionHandler(BindException.class)
    public String validExceptionHandler(BindException exception) {
        return exception.getAllErrors().get(0).getDefaultMessage();
    }
}
