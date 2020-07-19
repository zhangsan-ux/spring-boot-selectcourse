package com.personal.course.dto.sys.req;

import com.personal.course.dto.BasePageQueryDTO;
import lombok.Data;

import java.io.Serializable;

/**


@description 
@author cgc6828
@date 2020/4/13 17:30
*/
@Data
public class CourseQueryReq extends BasePageQueryDTO implements  Serializable {
    private static final long serialVersionUID = 3671631390164336065L;


    /**
     * 课程ID
     */
    private Long courseId;
    /**
     * 课程名称
     */
    private String courseName;
    /**
     * 课程学分
     */
    private Double courseCredit;
}
