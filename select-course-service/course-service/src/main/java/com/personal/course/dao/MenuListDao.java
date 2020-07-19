package com.personal.course.dao;

import com.personal.course.model.Menu;

import java.util.List;

/**
 * @author cgc6828
 * @description
 * @date 2020/5/6 20:27
 */
public interface MenuListDao {

    /**
     * 查出所有菜单
     * @return 菜单列表
     */
    List<Menu> listAllMenuList();

    /**
     * 根据角色Id查找菜单
     * @param roleId 角色Id
     * @return 菜单列表
     */
    List<Menu> listAllMenuListByRoleId(Long roleId);
}
