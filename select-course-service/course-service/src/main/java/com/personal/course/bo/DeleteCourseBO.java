package com.personal.course.bo;

import lombok.Data;

import java.io.Serializable;

/**


@description 
@author cgc6828
@date 2020/4/15 16:25
*/
@Data
public class DeleteCourseBO implements Serializable {
    private static final long serialVersionUID = -6347588938592293662L;

    private  Long userId;
}
