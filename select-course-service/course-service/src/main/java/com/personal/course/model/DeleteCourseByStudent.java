package com.personal.course.model;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**


@description 
@author cgc6828
@date 2020/4/18 9:05
*/
@Data
public class DeleteCourseByStudent implements Serializable {
    private static final long serialVersionUID = -4583000035203078603L;

    /**
     * 课程Id
     */
    private Long courseId;
}
