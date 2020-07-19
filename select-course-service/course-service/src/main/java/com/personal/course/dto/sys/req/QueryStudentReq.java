package com.personal.course.dto.sys.req;

import lombok.Data;

import java.io.Serializable;

/**


@description 
@author cgc6828
@date 2020/4/16 13:46
*/
@Data
public class QueryStudentReq implements Serializable {
    private static final long serialVersionUID = -8296423296204100093L;



    /**
     * 学号
     */
    private String businessNumber;
    /**
     * 姓名
     */
    private String  name ;
    /**
     * 课程名称
     */
    private String courseName;

    /**
     * 班级名称
     */
    private String className;
    /**
     * 班级名称
     */
    private String classroomName;
}
