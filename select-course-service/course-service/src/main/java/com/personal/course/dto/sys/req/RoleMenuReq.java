package com.personal.course.dto.sys.req;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

/**


@description  根据角色Id插批量入菜单 的请求类
@author cgc6828
@date 2020/5/5 10:12
*/
@Data
public class RoleMenuReq implements Serializable {
    private static final long serialVersionUID = 5591736308720022666L;

    /**
     * 角色Id
     */
    @Min(value = 1,message = "角色Id最小是1")
    private Long roleId;
    /**
     * 选中的菜单Id
     */
    private List<Long> menuIdList;
    /**
     * 角色名字
     */
    private String roleName;
    /**
     * 角色备注
     */
    @Size(max = 200, message = "备注长度非法")
    private String roleRemark;
}
