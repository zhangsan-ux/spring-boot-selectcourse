package com.personal.course.service.imp;

import com.personal.course.dao.MenuListDao;
import com.personal.course.model.Menu;
import com.personal.course.service.MenuManageService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author cgc6828
 * @description
 * @date 2020/5/6 20:42
 */
@Service
public class MenuManageServiceImpl implements MenuManageService {

    @Resource
    private MenuListDao menuListDao;

    @Override
    public List<Menu> selectAllMenuList() {
        return menuListDao.listAllMenuList();
    }

    @Override
    public List<Menu> listAllMenuListByRoleId(Long roleId) {
        return menuListDao.listAllMenuListByRoleId(roleId);
    }
}
