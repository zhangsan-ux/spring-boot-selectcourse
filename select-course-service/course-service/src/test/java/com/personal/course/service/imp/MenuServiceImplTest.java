package com.personal.course.service.imp;

import com.personal.common.util.FastJsonUtil;
import com.personal.course.CourseServiceApplicationTests;
import com.personal.course.model.Menu;
import com.personal.course.service.MenuManageService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author cgc6828
 * @description
 * @date 2020/5/20 9:36
 */
@Slf4j
public class MenuServiceImplTest extends CourseServiceApplicationTests {

    @Resource
    private MenuManageService menuManageService;

    @Test
    public void listAllMenuList() {
        List<Menu> rootMenu = menuManageService.selectAllMenuList();

        // 最后的结果
        List<Menu> menu = new ArrayList<>();
        // 先找到所有的一级菜单
        for (int i = 0; i < rootMenu.size(); i++) {
            // 一级菜单没有parentId
            if (rootMenu.get(i).getParentId() == 0) {
                menu.add(rootMenu.get(i));
            }
        }

        log.info("menuList={}", FastJsonUtil.toJson(menu));

        for (Menu menu1 : menu) {
            menu1.setChildMenus(getMenuChild(menu1.getMenuId(), rootMenu));
        }

        log.info("menuList1={}", FastJsonUtil.toJson(menu));
    }

    public  List<Menu> getMenuChild(Long menuId, List<Menu> rootMenu) {
        List<Menu> childMenus = new ArrayList<>();
        for (Menu menu : rootMenu) {
            if (menu.getParentId().equals(menuId)) {
                childMenus.add(menu);
            }
        }

        for (Menu menu : childMenus) {
            menu.setChildMenus(getMenuChild(menu.getMenuId(), rootMenu));
        }

        return childMenus;
    }
}