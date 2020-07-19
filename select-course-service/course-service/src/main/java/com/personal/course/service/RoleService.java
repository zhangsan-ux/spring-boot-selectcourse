package com.personal.course.service;

import com.github.pagehelper.PageInfo;
import com.personal.course.dto.sys.req.RoleQueryReq;
import com.personal.course.model.Role;

import java.util.List;

/**
 * @author cgc6828
 * @description
 * @date 2020/5/5 11:08
 */
public interface RoleService {

    /**
     * 插入一条角色
     * @param role 角色实体
     * @return 插入的条数
     */
    int insertRole(Role role);

    /**
     * 根据角色名称 查出 角色
     * @param roleName
     * @return
     */
    Role selectRoleByRoleName(String roleName);

    /**
     *分页查询角色
     * @param roleQueryReq 角色模糊查询条件
     * @return 角色列表
     */
    PageInfo<Role> roleListPage(RoleQueryReq roleQueryReq);

    /**
     * 根据角色Id 查出 角色实体
     * @param roleId 角色Id
     * @return 角色实体
     */
    Role selectRoleByRoleId(Long roleId);

    /**
     * 根据角色Id跟新 角色
     * @param role 角色实体类
     * @return 更新的条数
     */
    int updateRoleByRoleId(Role role);
    /**
     * 根据角色Id查找菜单列表
     * @param roleId 角色Id
     * @return 菜单列表
     */
    List<String> selectMenuByRoleId (Long roleId);
}
