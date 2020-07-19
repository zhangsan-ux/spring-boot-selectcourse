package com.personal.course.model;

import lombok.Data;

import java.io.Serializable;

/**


@description 
@author cgc6828
@date 2020/4/14 19:20
*/
@Data
public class UserCourse implements Serializable {
    private static final long serialVersionUID = -4980558842319184872L;

    /**
     * ID
     */
    private Long id;
    /**
     * 用户ID
     */
    private Long userId;
    /**
     * 课程 ID
     */
    private Long courseId;
    /**
     *  教室 ID
     */
    private Long classroomId;
}
