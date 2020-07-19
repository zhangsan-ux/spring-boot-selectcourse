package com.personal.course.dto.sys.req;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**


@description 
@author cgc6828
@date 2020/4/14 19:29
*/
@Data
public class UserCourseDTO implements Serializable {
    private static final long serialVersionUID = -3343905416399027873L;

    /**
     * 主键ID
     */
    private Long id;
    /**
     * 教室ID
     */
    private Long classroomId;
    /**
     * 课程ID
     */
    private List<Long> courseIds;


}
