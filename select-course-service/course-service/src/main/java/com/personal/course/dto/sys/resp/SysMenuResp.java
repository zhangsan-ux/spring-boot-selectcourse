package com.personal.course.dto.sys.resp;

import lombok.Data;

import java.io.Serializable;

/**
 * 说明
 *
 * @author cgc 6828
 * @date 2020/5/5 18:13
 */
@Data
public class SysMenuResp implements Serializable {
    private static final long serialVersionUID = -8778370979556410941L;

    /**
     * 菜单Id
     */
    private Long id;

    /**
     * 父Id
     */
    private Long pId;

    /**
     * 菜单名字
     */
    private String name;
    /**
     * 是否选中
     */
    private Boolean checked;


}
