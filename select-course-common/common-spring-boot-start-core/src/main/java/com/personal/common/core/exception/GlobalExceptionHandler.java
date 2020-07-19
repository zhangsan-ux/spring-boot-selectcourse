package com.personal.common.core.exception;

import com.personal.core.enums.ResultEnum;
import com.personal.core.exception.BusinessException;
import com.personal.core.util.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.List;

/**
 * @author cgc6828
 * @className GlobalExceptionHandler
 * @description TODO  全局异常处理
 * @date {DATE}{TIME}
 */
@RestControllerAdvice
@Slf4j
@ResponseBody
public class GlobalExceptionHandler {
    /**
     * 系统繁忙异常
     *
     * @param e 异常对象
     * @return object
     */
    @ExceptionHandler(value = Exception.class)
    public Object handleException(Exception e) {
        log.error("Exception异常内容:", e);
        return ResultUtil.getResult(ResultEnum.ERROR_SYS);
    }

    /**
     * 系统繁忙异常
     *
     * @param e 异常对象
     * @return object
     */
    @ExceptionHandler(value = BusinessException.class)
    public Object handleBusinessException(BusinessException e) {
        log.error("BusinessException异常内容:", e);
        return ResultUtil.getResult(e.getCode(), e.getMessage());
    }

    /**
     * IllegalArgumentException异常处理返回json
     * 返回状态码:400
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({IllegalArgumentException.class})
    public Object badRequestException(IllegalArgumentException e) {
        return ResultUtil.getResult(ResultEnum.PARAMETER_RESOLVE_FAIL);
    }

    /**
     * 返回状态码:405
     */
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
    public Object handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        return ResultUtil.getResult(ResultEnum.NO_SUPPORT_REQUEST_METHOD);
    }

    /**
     * 返回状态码:415
     */
    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    @ExceptionHandler({HttpMediaTypeNotSupportedException.class})
    public Object handleHttpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException e) {
        return ResultUtil.getResult(ResultEnum.NO_SUPPORT_REQUEST_MEDIA);
    }

    /**
     * 参数错误异常
     */
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public Object handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        List<ObjectError> objectErrorList = bindingResult.getAllErrors();
        return ResultUtil.getResult(ResultEnum.PARAMETER_ERROR.getCode(), objectErrorList.get(0).getDefaultMessage());
    }

    /**
     * 404异常
     */
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoHandlerFoundException.class)
    public Object noHandlerFoundException(NoHandlerFoundException e) {
        return ResultUtil.getResult(ResultEnum.NO_EXIST_INTERFACE);
    }

}


