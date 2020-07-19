package com.personal.course.dto.sys.req;

import lombok.Data;

import javax.validation.constraints.*;
import java.io.Serializable;

/**


@description 
@author cgc6828
@date 2020/4/13 18:23
*/
@Data
public class SaveCourseReq implements Serializable {
    private static final long serialVersionUID = -4781847408672913433L;

    /**
     * 课程Id
     */
    private Long courseId;
    /**
     * 课程名
     */
    @NotEmpty(message = "课程名不可以为空")
    @Size(max = 16,message = "课程名最大长度是16个字符")
    private String courseName;

    /**
     * 课程学分
     */
    @NotNull(message = "课程学分不可以为空")
    @Max(value = 6 ,message = "课程最高的学分是6")
    private Double courseCredit;

    /**
     * 创建人
     */
    private String createBy;
    /**
     * 更新人
     */
    private String updateBy;
}
