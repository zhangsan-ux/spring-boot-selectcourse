package com.personal.course.exception;


import com.personal.core.enums.ResultEnum;

/**
 * 说明
 *
 * @author cgc 6828
 * @date 2020/5/20 12:41
 */
public class BusinessException extends RuntimeException {
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
        throw new com.personal.core.exception.BusinessException(returnEnum.getCode(), msg);
    }
}
