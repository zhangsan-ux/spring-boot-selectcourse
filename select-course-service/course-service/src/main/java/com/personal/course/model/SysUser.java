package com.personal.course.model;

import lombok.Data;

import java.util.Date;

/**
 * @author cgc6828
 * @className User
 * @description 用户实体类
 * @date {DATE}{TIME}
 */
@Data
public class SysUser {
 
  private Long userId;

    private String name;

  private String userPassword;

  private Integer userSex;

    private String className;
    private String imageUrl;
    private String salt;
    private Long roleId;
    private String idCard;
    private String mobile;
    private String businessNumber;
    private Integer pwdStatus;
    private String createBy;
    private Date createTime;
    private String updateBy;
    private Date updateTime;


}


