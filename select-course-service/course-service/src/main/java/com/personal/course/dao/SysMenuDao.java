package com.personal.course.dao;

import com.personal.course.model.Role;
import com.personal.course.model.RoleMenu;
import com.personal.course.model.SysMenu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**


@description 菜单接口
@author cgc6828
@date 2020/5/4 17:54
*/
public interface SysMenuDao {

    /**
     * 根据userid 查出用户的所有菜单
     *
     * @return 菜单功能
     */
    List<SysMenu> selectAllMenu();

    /**
     * 根据rodeId查找菜单 信息
     * @param roleId 角色Id
     * @return 角色
     */
    Role selectMenuInfoByRoleId(Long roleId);


    /**
     * 根据角色Id查出 所以有的 角色菜单中间类
     * @param roleId 角色Id
     * @return  角色菜单中间类集合
     */
    List<RoleMenu> selectAllMenuByRoleId(Long roleId);

    /**
     * 根据roleId 删除角色中间角色Id表对应的菜单
     * @param roleId 角色Id
     * @return 删除的条数
     */
    int deleteAllMenuByRoleId(@Param("roleId") Long roleId);

    /**
     * 根据roleId批量插入 菜单列表
     * @param menuIdList 菜单列表
     *@param roleId 角色
     * @return 插入的条数
     */
    int insertMenusByRoleId(@Param("roleMenuList") List<Long> menuIdList,@Param("roleId") Long  roleId);

    /**
     * 根据菜单Id集合查出 菜单列表
     * @param menuIdList 菜单Id集合
     * @return
     */
    List<SysMenu> listMenuByMenuIds (@Param("menuIdList") List<Long> menuIdList);
}
