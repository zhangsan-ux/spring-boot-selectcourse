package com.personal.course.service;

import com.personal.course.model.Menu;

import java.util.List;

/**
 * @author cgc6828
 * @description
 * @date 2020/5/6 20:42
 */
public interface MenuManageService {

    /**
     * 查出所有菜单
     * @return 菜单列表
     */
    List<Menu> selectAllMenuList();


    /**
     * 根据角色Id查找菜单
     * @param roleId 角色Id
     * @return 菜单列表
     */
    List<Menu> listAllMenuListByRoleId(Long roleId);
}
