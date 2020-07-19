package com.personal.course.model;

import lombok.Data;

import java.io.Serializable;

/**


@description 
@author cgc6828
@date 2020/5/5 11:09
*/
@Data
public class Role implements Serializable {
    private static final long serialVersionUID = -5141673118189911285L;

    /**
     * 角色Id
     */
    private Long  roleId;
    /**
     * 角色名称
     */
    private String roleName;
    /**
     * 角色备注
     */
    private String roleRemark;
    /**
     * 创建人
     */
    private String createBy;
    /**
     * 创建人
     */
    private String createTime;
    /**
    /**
     * 更新人
     */
    private String updateBy;
}
