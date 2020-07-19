package com.personal.course.model;

import lombok.Data;

import java.util.Date;

/**

@className Course
@description 
@author cgc6828
@date 2020/4/10 19:02
*/
@Data
public class Course {


    /**
     * 课程编号
     */
    private Long courseId;
    /**
     * 课程名称
     */
    private String  courseName;
    /**
     * 课程学分
     */
    private Double courseCredit;
    /**
     * 创建人
     */
    private String createBy;
    /**
     * 创建时间
     */

    private Date createTime;
    /**
     * 更新人
     */
    private String updateBy;
    /**
     * 跟新时间
     */
    private Date updateTime;
}
