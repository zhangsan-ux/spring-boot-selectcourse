package com.personal.core.util;

import com.personal.core.dto.ResponseDataDTO;
import com.personal.core.enums.ResultEnum;

/**
 * @author cgc6828
 * @className ResultUtil
 * @description TODO
 * @date {DATE}{TIME}
 */
public class ResultUtil {

    /**
     * 获取成功信息
     *
     * @param data object data
     * @param <T>  object
     * @return responseDataDTO
     */
    public static <T> ResponseDataDTO<T> getSuccessResult(T data) {
        ResponseDataDTO<T> responseDataDTO = new ResponseDataDTO<>();
        responseDataDTO.setCode(ResultEnum.SUCCESS.getCode());
        responseDataDTO.setMessage(ResultEnum.SUCCESS.getMsg());
        responseDataDTO.setData(data);
        return responseDataDTO;
    }

    /**
     * 获取成功信息
     *
     * @param <T> object
     * @return responseDataDTO
     */
    public static <T> ResponseDataDTO<T> getSuccessResult() {
        ResponseDataDTO<T> responseDataDTO = new ResponseDataDTO<>();
        responseDataDTO.setCode(ResultEnum.SUCCESS.getCode());
        responseDataDTO.setMessage(ResultEnum.SUCCESS.getMsg());
        return responseDataDTO;
    }

    /**
     * 获取枚举信息
     *
     * @param resultEnum resultEnum
     * @param <T>        object
     * @return responseDataDTO
     */
    public static <T> ResponseDataDTO<T> getResult(ResultEnum resultEnum) {
        ResponseDataDTO<T> responseDataDTO = new ResponseDataDTO<>();
        responseDataDTO.setCode(resultEnum.getCode());
        responseDataDTO.setMessage(resultEnum.getMsg());
        return responseDataDTO;
    }

    /**
     * 获取信息
     *
     * @param code    响应编码
     * @param message 消息
     * @param <T>     object
     * @return responseDataDTO
     */
    public static <T> ResponseDataDTO<T> getResult(int code, String message) {
        ResponseDataDTO<T> responseDataDTO = new ResponseDataDTO<>();
        responseDataDTO.setCode(code);
        responseDataDTO.setMessage(message);
        return responseDataDTO;
    }

    /**
     * 获取枚举信息
     *
     * @param resultEnum resultEnum
     * @param msg        msg
     * @param <T>        object
     * @return responseDataDTO
     */
    public static <T> ResponseDataDTO<T> getResult(ResultEnum resultEnum, String msg) {
        ResponseDataDTO<T> responseDataDTO = new ResponseDataDTO<>();
        responseDataDTO.setCode(resultEnum.getCode());
        responseDataDTO.setMessage(msg);
        return responseDataDTO;
    }

    /**
     * 获取系统异常信息
     *
     * @param msg 异常信息
     * @param <T> object
     * @return responseDataDTO
     */
    public static <T> ResponseDataDTO<T> getSystemErrorResult(String msg) {
        ResponseDataDTO<T> responseDataDTO = new ResponseDataDTO<>();
        responseDataDTO.setCode(ResultEnum.ERROR_SYS.getCode());
        responseDataDTO.setMessage(msg);
        return responseDataDTO;
    }

    /**
     * 获取服务异常信息
     *
     * @param <T> object
     * @return responseDataDTO
     */
    public static <T> ResponseDataDTO<T> getServiceErrorResult() {
        ResponseDataDTO<T> responseDataDTO = new ResponseDataDTO<>();
        responseDataDTO.setCode(ResultEnum.ERROR_SERVICE.getCode());
        responseDataDTO.setMessage(ResultEnum.ERROR_SERVICE.getMsg());
        return responseDataDTO;
    }
}

