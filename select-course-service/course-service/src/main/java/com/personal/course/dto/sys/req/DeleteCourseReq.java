package com.personal.course.dto.sys.req;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.List;

/**


@description 
@author cgc6828
@date 2020/4/15 17:01
*/
@Data
public class DeleteCourseReq implements Serializable {
    private static final long serialVersionUID = -5902181144557213775L;
    /**
     * 用户Id
     */
    private Long userId;
    /**
     * 课程Id
     */
 @Min(value = 1,message = "courseId不可以小于0")
 @NotEmpty(message = "courseId 不可以为空")
    private List<Long> courseIds;
}
