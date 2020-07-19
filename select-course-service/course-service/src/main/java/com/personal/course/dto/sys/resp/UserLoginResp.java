package com.personal.course.dto.sys.resp;

import com.personal.course.model.Menu;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**

@className UserLoginResp
@description 
@author cgc6828
@date 2020/4/11 18:15
*/
@Data
public class UserLoginResp implements Serializable {
    private static final long serialVersionUID = 485841463141159540L;

    private Long userId;
    /**
     * 姓名
     */
    private String name;
    /**
     * 性别
     */
    private String  userSex;
    /**
     * 班级名称
     */
    private String className;
    /**
     * 头像
     */
    private String imageUrl;
    /**
     * 手机
     */
    private  String mobile;

    /**
     * 角色ID
     */
    private  Long roleId;

    /**
     * 菜单列表
     */
    private List<Menu>  menus;

    /**
     * 按钮
     */
    private List<String> permissions;
}
