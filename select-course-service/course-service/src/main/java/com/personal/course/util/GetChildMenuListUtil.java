package com.personal.course.util;

import com.personal.course.model.Menu;

import java.util.ArrayList;
import java.util.List;

/**
 * @author cgc6828
 * @description
 * @date 2020/5/6 20:48
 */
public class GetChildMenuListUtil {

    public static List<Menu> getMenuChild(Long menuId, List<Menu> rootMenu) {

        // 子菜单
        List<Menu> childMenus = new ArrayList<>();

    for (Menu menu : rootMenu) {
        if (menu.getParentId().equals(menuId)) {
            childMenus.add(menu);
        }

    }

       for (Menu menu :childMenus) {
           menu.setChildMenus(GetChildMenuListUtil.getMenuChild(menu.getMenuId(),rootMenu));
       }
        return childMenus;

    }
}
