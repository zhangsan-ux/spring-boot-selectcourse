package com.personal.course.dto.sys.req;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**

@className UserLoginReq
@description 
@author cgc6828
@date 2020/4/11 17:39
*/
@Data
public class UserLoginReq implements Serializable {

    private static final long serialVersionUID = 7682242741258187167L;


    /**
     * 学号或者教工号
     */
    @NotNull(message = "学号或者教工号不可以为空")
    @Size(min = 11,max = 11,message = "学号或者教工号长度为11")
    private String businessNumber;
    /**
     *密码
     */
    @NotNull(message = "密码不可以为空")
    @Size(min = 6,max = 64,message = "密码长度为6~64个字符")
    private  String userPassword;
    /**
     * 图形验证码
     */
   // @NotNull(message = "验证码不能为空")
   // @Size(min = 2, max = 255, message = "验证码长度非法")
    private String imgCode;
    /**
     * 图形验证码key
     */
    //@NotNull(message = "请重新获取图形验证码")
   // @Size(min = 2, max = 255, message = "请重新获取图形验证码")
    private String codeKey;



}
