package com.personal.course.dto.sys.req;

import com.personal.course.dto.BasePageQueryDTO;
import lombok.Data;

import java.io.Serializable;

/**


@description 
@author cgc6828
@date 2020/5/5 16:16
*/
@Data
public class RoleQueryReq extends BasePageQueryDTO implements Serializable {
    private static final long serialVersionUID = 7118771524394105915L;


    /**
     * 角色名称
     */
    private String roleName;
}
