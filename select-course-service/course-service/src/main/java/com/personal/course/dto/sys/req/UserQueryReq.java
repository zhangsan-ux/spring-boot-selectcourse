package com.personal.course.dto.sys.req;

import com.personal.course.dto.BasePageQueryDTO;
import lombok.Data;

import javax.validation.constraints.Size;
import java.io.Serializable;

/**

@className UserQueryReq
@description 
@author cgc6828
@date 2020/4/12 11:16
*/
@Data
public class UserQueryReq extends BasePageQueryDTO implements Serializable {


    private static final long serialVersionUID = -1145915235363947029L;

    /**
     * 用户名称
     */
    @Size(max = 20, message = "用户名长度非法")
    private String name;


    /**
     * 手机号码
     */
    @Size(max = 20, message = "手机号码长度非法")
    private String mobile;
}
