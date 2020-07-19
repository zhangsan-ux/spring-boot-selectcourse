package com.personal.course.model;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**


@description  根据Id 更新 用户对应的上课的教室
@author cgc6828
@date 2020/4/15 21:21
*/
@Data
public class UserCourseInfo implements Serializable {


    private static final long serialVersionUID = 8957769526837707484L;
    /**
     * 主键Id
     */
    @NotNull(message = "id不可以为空")
    @Min(value = 1 , message = "id不可以小于0")
    private Long id;
    /**
     * 教室Id
     */

    @Min(value = 1 , message = "教室Id不可以小于0")
    private Long classroomId;
}
