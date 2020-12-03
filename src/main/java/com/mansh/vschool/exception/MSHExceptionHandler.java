package com.mansh.vschool.exception;

import cn.hutool.core.exceptions.ExceptionUtil;
import com.mansh.vschool.custom.ResultData;
import com.mansh.vschool.utils.CommonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@Slf4j
public class MSHExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResultData<String> handleException(Exception e){
        log.error(CommonUtils.exceptionToString(e));
        String code = "";
        if(e instanceof MSHException){
            code = "-1";
        }else if(e instanceof RuntimeException){
            code = "-2";
        }else if(e instanceof Exception){
            code = "-3";
        }
        return new ResultData<>().setCode(code).setSuccess(false).setMessage(ExceptionUtil.getSimpleMessage(e));
    }

}
