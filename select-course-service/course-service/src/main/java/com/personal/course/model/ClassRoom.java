package com.personal.course.model;

import lombok.Data;

import java.io.Serializable;

/**


@description 
@author cgc6828
@date 2020/4/13 22:17
*/
@Data
public class ClassRoom implements Serializable {
    private static final long serialVersionUID = -8872142978043543671L;

    /**
     * 教室ID
     */
    private Long classroomId;

    /**
     * 教室名称
     */
    private  String classroomName;
}
