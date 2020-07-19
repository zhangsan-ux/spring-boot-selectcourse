package com.personal.course.service;

import com.personal.common.util.FastJsonUtil;
import com.personal.course.model.RoleMenu;
import com.personal.course.model.SysMenu;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.List;

/**
 * @author cgc6828
 * @description
 * @date 2020/5/5 12:31
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class SysMenuServiceTest {

    @Resource
    private SysMenuService sysMenuService;

    @Resource
    private RoleService roleService;
    @Test
    public void selectAllMenu() {
        List<SysMenu> sysMenus = sysMenuService.selectAllMenu();
        for (SysMenu sysMenu :sysMenus) {
          log.info("查出所有的菜单是 ，{}",sysMenu);
        }
    }

    @Test
    public void selectMenuInfoByRoleId() {
        Long roleId = 1L;
        //根据 角色Id 查出 角色中间表的 实体类
        List<RoleMenu> roleMenuList = sysMenuService.listAllMenuByRoleId(roleId);
    }

    @Test
    public void selectAllMenuByRoleId() {
    }

    @Test
    public void insertMenusByRoleId() {
        List<Long> roleMenuList = new ArrayList<>();
        roleMenuList.add(2L);
        roleMenuList.add(3L);
        roleMenuList.add(4L);
        Long roleId = 3L;
        int i  = sysMenuService.insertMenusByRoleId(roleMenuList,roleId);
        log.info("插入的条数是 ，{} 条",i);
    }

  @Test
    public  void deleteMenuByRoleId(){
        Long roleId = 2L;

        int i =   sysMenuService.deleteAllMenuByRoleId(roleId);
        if (i > 0){
            log.info("成功的删除，{} 条数据",i);
        }

    }

    @Test
    public void  listMenuByMenuIds(){
        List<Long>  menuIdsList  = new ArrayList<>();
        menuIdsList.add(1L);
        menuIdsList.add(2L);
        menuIdsList.add(3L);
        menuIdsList.add(4L);
        menuIdsList.add(5L);
        List<SysMenu> sysMenuList = sysMenuService.listMenuByMenuIds(menuIdsList);
        log.info("查出的菜单是 : sysMenuList={}", FastJsonUtil.toJson(sysMenuList));

    }
}