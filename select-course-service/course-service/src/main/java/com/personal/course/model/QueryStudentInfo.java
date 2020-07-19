package com.personal.course.model;

import lombok.Data;

import java.io.Serializable;

/**


@description  根据条件查询综合学生信息的返回实体类
@author cgc6828
@date 2020/4/16 13:29
*/

@Data
public class QueryStudentInfo  implements Serializable {


    private static final long serialVersionUID = 8406714274716937572L;

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
    /**
     * 性别
     */
    private Integer userSex;
    /**
     * 总学分
     */
    private Double sumCredit;


}
