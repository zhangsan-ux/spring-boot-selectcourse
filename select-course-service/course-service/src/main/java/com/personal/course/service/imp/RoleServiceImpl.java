package com.personal.course.service.imp;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.personal.course.dao.RoleDao;
import com.personal.course.dto.sys.req.RoleQueryReq;
import com.personal.course.model.Role;
import com.personal.course.service.RoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author cgc6828
 * @description
 * @date 2020/5/5 11:08
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Resource
    private RoleDao roleDao;

    @Override
    public int insertRole(Role role) {
        return roleDao.insertRole(role);
    }

    @Override
    public Role selectRoleByRoleName(String roleName) {
        return roleDao.selectRoleByRoleName(roleName);
    }

    @Override
    public PageInfo<Role> roleListPage(RoleQueryReq roleQueryReq) {
        PageHelper.startPage(roleQueryReq.getPageNum(), roleQueryReq.getPageSize());
        return new PageInfo<>(roleDao.roleListPage(roleQueryReq));
    }

    @Override
    public Role selectRoleByRoleId(Long roleId) {
        return roleDao.selectRoleByRoleId(roleId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateRoleByRoleId(Role role) {
        return roleDao.updateRoleByRoleId(role);
    }

    @Override
    public List<String> selectMenuByRoleId(Long roleId) {
        return roleDao.selectMenuByRoleId(roleId);
    }


}
