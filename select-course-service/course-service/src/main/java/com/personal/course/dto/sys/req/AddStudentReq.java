package com.personal.course.dto.sys.req;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**

@className AddStudentReq
@description 
@author cgc6828
@date 2020/4/12 17:37
*/
@Data
public class AddStudentReq implements Serializable {
    private static final long serialVersionUID = 5876109279859982436L;


    /**
     * 用户ID
     */
    @Max(value=64 ,message = "用户ID不可以超过64位")
    private Long userId;


    /**
     * 姓名
     */
    @NotNull(message = "姓名不可以为空")
    @Size(max = 16,message = "名字最大长度是16个字符")
    private String name;
;
    /**
     * 班级
     */
    @NotNull(message = "班级不可以为空")
    @Size(max = 16,message = "班级最大长度是16个字符")
    private String className;

    /**
     * 性别
     */
    @NotNull(message = "性别不可以为空")
    @Size(max = 4,message = "性别最大长度是4个字符")
    private String userSex;

    /**
     * 头像
     */
    private String imageUrl="/333/555/44/123.png";

    /**
     * 角色 默认是学生
     */
    private Long roleId =3L;
    /**
     * 身份证
     */
    @NotNull(message = "身份证不可以为空")
    @Size(max = 18,min =18, message = "身份证长度是18个字符")
    private  String idCard;
    /**
     * 手机
     */
    @Size(max = 11 ,min = 11, message = "手机号码长度应该为11个数字")
    @NotNull(message = "手机号不可以为空！")
    @Pattern(regexp ="/^1[3456789]\\d{9}$/", message = "手机号非法")
    private String mobile;

    /**
     * 学号或者教工号
     */
    @Size(max = 11 ,min = 11, message = "手机号码长度应该为11个数字")
    @NotNull(message = "学号或者教工号不可以为空")
    private String businessNumber;
    /**
     * 密码状态 1-未修改 未修改密码是身份证后6位 2-修改
     */
    private Integer pwdStatus;
    /**
     * 创建人
     */
    private String createBy;
    /**
     * 更新人
     */
    private String updateBy;
}
