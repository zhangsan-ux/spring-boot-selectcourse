package com.personal.core.enums;

/**
 * @author cgc6828
 * @className ResultEnum
 * @description TODO
 * @date {DATE}{TIME}
 */
public enum ResultEnum {
    /**
     * 用户状态异常
     */
    USER_STATUS_ERROR(1000, "用户状态异常"),
    /**
     * 参数错误
     */
    PARAMETER_ERROR(1110, "参数错误"),
    /**
     * 系统繁忙
     */
    ERROR_SYS(1111, "系统繁忙"),
    /**
     * 服务繁忙,请稍后再试
     */
    ERROR_SERVICE(1112, "服务繁忙,请稍后再试"),
    /**
     * 参数解析失败
     */
    PARAMETER_RESOLVE_FAIL(1113, "参数解析失败"),
    /**
     * 不支持当前请求方法
     */
    NO_SUPPORT_REQUEST_METHOD(1114, "不支持当前请求方法"),
    /**
     * 不支持当前媒体类型
     */
    NO_SUPPORT_REQUEST_MEDIA(1115, "不支持当前媒体类型"),
    /**
     * 业务异常
     */
    BUSINESS_ERROR(1116, "业务异常"),
    /**
     * 不支持当前媒体类型
     */
    NO_EXIST_INTERFACE(1117, "不存在的接口调用"),
    /**
     * 验证码已过期
     */
    VALID_CODE_EXPIRED(1118, "验证码已过期"),
    /**
     * 成功
     */
    SUCCESS(9999, "成功");

    private Integer code;
    private String msg;


    ResultEnum(Integer code, String msg) {
        this.msg = msg;
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}

