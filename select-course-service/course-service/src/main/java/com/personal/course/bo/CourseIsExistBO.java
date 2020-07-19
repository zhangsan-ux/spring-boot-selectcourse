package com.personal.course.bo;

import lombok.Data;

import java.io.Serializable;

/**


@description 
@author cgc6828
@date 2020/4/14 20:55
*/
@Data
public class CourseIsExistBO implements Serializable {


    private static final long serialVersionUID = -3294142275816470156L;

    /**
     * 课程Id
     */
    private Long courseId;
    /**
     * 课程名称
     */
    private String courseName;
    /**
     * 课程名称
     */
    private Double courseCredit;

}
