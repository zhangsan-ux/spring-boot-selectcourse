package com.personal.course.dto.sys.resp;

import lombok.Data;

import java.io.Serializable;

/**


@description 
@author cgc6828
@date 2020/4/13 15:14
*/
@Data
public class StudentEditResp implements Serializable {
    private static final long serialVersionUID = -4634188459548455438L;


    /**
     * 用户Id
     */
    private Long userId;

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

    /**
     * 身份证
     */
    private String idCard;
}
