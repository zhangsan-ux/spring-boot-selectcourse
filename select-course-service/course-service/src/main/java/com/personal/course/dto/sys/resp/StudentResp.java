package com.personal.course.dto.sys.resp;

import lombok.Data;

import java.io.Serializable;

/**

@className StudentResp
@description  学生列表响应类
@author cgc6828
@date 2020/4/12 10:18
*/
@Data
public class StudentResp  implements Serializable {

    private static final long serialVersionUID = -4634188459548455438L;
    /**
     * 学号或者教师号
     */
    private  String businessNumber;
    /**
     * 姓名
     */
    private String name;
    /**
     * 班级名称
     */
    private String className;
    /**
     * 性别
     */
    private String userSex;
    /**
     * 手机
     */
    private  String mobile;
}
