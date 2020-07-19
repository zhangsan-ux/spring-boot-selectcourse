package com.personal.core.exception;

import com.personal.core.enums.ResultEnum;

/**
 * @author cgc6828
 * @className BusinessException
 * @description TODO
 * @date {DATE}{TIME}
 */
public class BusinessException extends  RuntimeException {
    private static final long serialVersionUID = 341185853561929991L;
    private Integer code;
    private String msg;

    public String getMsg() {
        return msg;
    }

    public Integer getCode() {
        return code;
    }

    public BusinessException(Integer code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    public BusinessException(ResultEnum returnEnum) {
        super(returnEnum.getMsg());
        this.code = returnEnum.getCode();
        this.msg = returnEnum.getMsg();
    }

    public static void err(ResultEnum returnEnum, String msg) {
        throw new BusinessException(returnEnum.getCode(), msg);
    }
}


