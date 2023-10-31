package com.linynag.springbootinit.exception;

import com.linynag.springbootinit.common.BaseResponse;
import com.linynag.springbootinit.common.ErrorCode;
import com.linynag.springbootinit.common.ResultUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 全局异常处理器
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @Autowired
    HttpServletRequest httpServletRequest;

    @ExceptionHandler(BusinessException.class)
    public BaseResponse<T> businessExceptionHandler(BusinessException e) {
        log.error("BusinessException", e);
        return ResultUtils.error(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    public BaseResponse<T> runtimeExceptionHandler(RuntimeException e) {
        log.error("RuntimeException", e);
        return ResultUtils.error(ErrorCode.SYSTEM_ERROR, "系统错误");
    }

    private void logErrorRequest(Exception e) {
        // 组装日志内容
        String logInfo = String.format("报错API URL: %S, error = ", httpServletRequest.getRequestURI(), e.getMessage());
        // 打印日志
        log.error(logInfo);
    }

    /**
     * {@code @RequestBody} 参数校验不通过时抛出的异常处理
     */
    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseBody
    public BaseResponse<T> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        // 打印日志
        logErrorRequest(ex);
        // 组织异常信息，可能存在多个参数校验失败
        BindingResult bindingResult = ex.getBindingResult();
        StringBuilder sb = new StringBuilder("校验失败:");
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            sb.append(fieldError.getField()).append("：").append(fieldError.getDefaultMessage()).append(", ");
        }
        return ResultUtils.error(ErrorCode.PARAMS_ERROR, sb.toString());
    }


    /**
     * 其他异常
     *
     * @param exception
     * @return
     */
    @ExceptionHandler({HttpClientErrorException.class, IOException.class, Exception.class})
    protected BaseResponse<?> serviceException(Exception exception) {
        logErrorRequest(exception);
        return ResultUtils.error(ErrorCode.SYSTEM_ERROR, exception.getMessage());
    }
}
