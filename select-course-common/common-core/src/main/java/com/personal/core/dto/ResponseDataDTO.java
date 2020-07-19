package com.personal.core.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author cgc6828
 * @className ResponseDataDTO
 * @description TODO
 * @date {DATE}{TIME}
 */
@Data
public class ResponseDataDTO<T> implements Serializable {
    private static final long serialVersionUID = 7199667761005759015L;

    /**
     * 状态码
     */
    private Integer code;
    /**
     * 消息
     */
    private String message;
    /**
     * 数据
     */
    private T data;
}


