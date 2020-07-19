package com.personal.course.bo;

import lombok.Data;

import java.io.Serializable;

/**


@description 
@author cgc6828
@date 2020/4/15 17:56
*/
@Data
public class StudentInfo implements Serializable {
    private static final long serialVersionUID = -7699359846177622147L;

    /**
     * 用户Id
     */
    private  Long userId;
    /**
     *名字
     */
   private String name;
    /**
     *班 级
     */
   private String className;
    /**
     *性别
     */
   private Integer userSex;
    /**
     *头像
     */
   private  String imageUrl;
    /**
     *电话
     */
   private String mobile;
    /**
     *身份证
     */

   private String idCard;
    /**
     * 学号
     */
   private String businessNumber;
    /**
     *总学分
     */
   private Double sumCredit;

}
