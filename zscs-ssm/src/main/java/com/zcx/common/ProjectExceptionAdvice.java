package com.zcx.common;

import com.zcx.controller.Return;
import com.zcx.exception.BusinessException;
import org.omg.CORBA.SystemException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//@RestControllerAdvice
public class ProjectExceptionAdvice {

    @ExceptionHandler(SystemException.class)
    public Return doException(SystemException e){
        return Return.error(e.getMessage());
    }

    @ExceptionHandler(BusinessException.class)
    public Return doException(BusinessException e){
        return Return.error(e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public Return doException(Exception e){
        return Return.error("系统繁忙！稍后再试");
    }
}
