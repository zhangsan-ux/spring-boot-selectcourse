package com.personal.course.service.imp;

import com.personal.course.dao.SysMenuDao;

import com.personal.course.model.Role;
import com.personal.course.model.RoleMenu;
import com.personal.course.model.SysMenu;
import com.personal.course.service.SysMenuService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**


@description 
@author cgc6828
@date 2020/5/4 17:57
*/
@Service
public class SysMenuServiceImpl implements SysMenuService {

    @Resource
    private SysMenuDao sysMenuDao;

    @Override
    public List<SysMenu> selectAllMenu() {
        return sysMenuDao.selectAllMenu();
    }

    @Override
    public Role getMenuInfoByRoleId(Long roleId) {
        return sysMenuDao.selectMenuInfoByRoleId(roleId);
    }

    @Override
    public List<RoleMenu> listAllMenuByRoleId(Long roleId) {
        return sysMenuDao.selectAllMenuByRoleId(roleId);
    }

    @Override
    public int insertMenusByRoleId(List<Long> menuIdList, Long roleId) {
        return sysMenuDao.insertMenusByRoleId(menuIdList,roleId);
    }



    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteAllMenuByRoleId(Long roleId) {
        return sysMenuDao.deleteAllMenuByRoleId(roleId);
    }

    @Override
    public List<SysMenu> listMenuByMenuIds(List<Long> menuIdList) {
        return sysMenuDao.listMenuByMenuIds(menuIdList);
    }
}
