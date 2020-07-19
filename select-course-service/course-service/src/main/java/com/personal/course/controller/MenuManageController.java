package com.personal.course.controller;

import com.personal.core.enums.ResultEnum;
import com.personal.core.util.ResultUtil;
import com.personal.course.model.Menu;
import com.personal.course.service.MenuManageService;
import com.personal.course.util.GetChildMenuListUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author cgc6828
 * @description
 * @date 2020/5/6 21:06
 */
@RestController
@Slf4j
@RequestMapping("/menuManage")
public class MenuManageController {

    @Resource
    private MenuManageService menuManageService;

    @RequestMapping("/listAllMenu")
    public Object listAllMenuList() {

        List<Menu> rootMenu = menuManageService.selectAllMenuList();

        // 最后的结果
        List<Menu> menuList = new ArrayList<>();
        // 先找到所有的一级菜单
        for (int i = 0; i < rootMenu.size(); i++) {
            // 一级菜单没有parentId
            if (rootMenu.get(i).getParentId() == 0) {
                menuList.add(rootMenu.get(i));
            }
        }

        // 为一级菜单设置子菜单，getChild是递归调用的
        for (Menu menu : menuList) {
            menu.setChildMenus(GetChildMenuListUtil.getMenuChild(menu.getMenuId(), rootMenu));
        }
        return ResultUtil.getSuccessResult(menuList);
    }

    @GetMapping("/listAllMenuListByRoleId")
    public Object listAllMenuListByRoleId(Long roleId) {
        if (roleId == null || roleId < 0) {
            return ResultUtil.getResult(ResultEnum.PARAMETER_ERROR.getCode(), "roleId非法！");
        }
        List<Menu> rootMenu = menuManageService.listAllMenuListByRoleId(roleId);
        List<Menu> menuList = new ArrayList<>();
        // 最后的结果
        if (rootMenu != null && !rootMenu.isEmpty()) {

            // 先找到所有的一级菜单
            for (int i = 0; i < rootMenu.size(); i++) {
                // 一级菜单没有parentId
                if (rootMenu.get(i).getParentId() == 0) {
                    menuList.add(rootMenu.get(i));
                }
            }
            for (Menu menu1 : menuList) {
                menu1.setChildMenus(GetChildMenuListUtil.getMenuChild(menu1.getMenuId(), rootMenu));
            }

            // 为一级菜单设置子菜单，getChild是递归调用的
            for (Menu menu : menuList) {
                menu.setChildMenus(GetChildMenuListUtil.getMenuChild(menu.getMenuId(), rootMenu));
            }
        }
        return ResultUtil.getSuccessResult(menuList);
    }

}
