package com.personal.course.controller;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * @author cgc6828
 * @className AddStudentReq
 * @description
 * @date 2020/4/12 17:37
 */
@Data
public class AddStudentReq2 implements Serializable {
    private static final long serialVersionUID = 5876109279859982436L;


    @NotNull(message = "手机号不可以为空")
    @Size(max = 11, min = 11, message = "手机号码长度应该为11个数字")
    @Pattern(regexp = "^1[3456789]\\d{9}$", message = "手机号非法")
    private String mobile;

}
