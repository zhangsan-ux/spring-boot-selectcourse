package com.personal.course.model;

import lombok.Data;

import java.io.Serializable;

/**


@description  角色中间表实体类
@author cgc6828
@date 2020/5/5 8:48
*/
@Data
public class RoleMenu implements Serializable {
    private static final long serialVersionUID = 6072156003900095690L;
    /**
     * id
     */
    private Long id;
    /**
     * 角色id
     */
    private Long roleId;

    /**
     * 菜单id
     */
    private Long menuId;

}
