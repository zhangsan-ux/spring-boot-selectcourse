package com.personal.course.model;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**


@description 
@author cgc6828
@date 2020/5/6 20:15
*/
@Data
public class Menu implements Serializable {
    private static final long serialVersionUID = -5011690926583264011L;

    /**
     * 菜单Id
     */
    private Long menuId;

    /**
     * 父Id
     */
    private Long parentId;

    /**
     * 菜单名字
     */
    private String name;

    /**
     * 菜单路径
     */
    private String  url;
    /**
     * 授权
     */
    private String perms;

    /**
     * 排序
     */
    private  Integer orderNum;
    /**
     * 类型 0-目录 1-菜单 2-按钮
     */

    private Integer type;


    /**
     * 子菜单
     */
    private List<Menu> childMenus;
}
