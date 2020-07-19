package com.personal.course.bo;

import lombok.Data;

import java.io.Serializable;

/**


@description   修改教室
@author cgc6828
@date 2020/4/15 20:05
*/
@Data
public class CourseClassRoomInfo implements Serializable {
    private static final long serialVersionUID = -119429912709257016L;
    /**
     * 主键Id
     */
    private Long  id;

    /**
     * 用户Id
     */
    private Long userId;
    /**
     * 用户名
     */
    private String name;
    /**
     * 课程Id
     */
    private Long courseId;
    /**
     * 课程 名称
     */
    private String courseName;
    /**
     * 教室
     */
    private String classRoom;
    /**
     * 教室Id
     */
    private Long classroomId;
}
