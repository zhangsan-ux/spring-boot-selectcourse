package com.personal.course.dto;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**

@className CourseDTO
@description 
@author cgc6828
@date 2020/4/10 21:29
*/
@Data
public class CourseDTO implements Serializable {
    private static final long serialVersionUID = 8618247742977697271L;

    /**
     * 课程编号
     */
    @NotNull(message = "课程ID不可以为空！")
    private Long courseId;
    /**
     * 课程名称
     */
     @NotNull(message = "课程名称不可以为空！")
     @Size ( message = "课程名称应该在2到50个字符之间" ,min = 2,max = 50)
    private String  courseName;
    /**
     * 课程学分
     */
    @NotNull(message = "课程学分不可以为空！")

    @Min(value = 1, message = "每门课程的最低学分是1")
    @Max(value = 6, message =  "每门课程的最高学分是6")
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
