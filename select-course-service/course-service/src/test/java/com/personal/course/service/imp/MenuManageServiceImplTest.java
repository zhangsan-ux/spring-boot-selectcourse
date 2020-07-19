package com.personal.course.service.imp;

import com.personal.common.util.FastJsonUtil;
import com.personal.course.model.Menu;
import com.personal.course.service.MenuManageService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @author cgc6828
 * @description
 * @date 2020/5/25 21:45
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class MenuManageServiceImplTest {

    @Resource
    private MenuManageService menuManageService;

    @Test
    public void listAllMenuListByRoleId() {
        List<Menu> menuList = menuManageService.listAllMenuListByRoleId(24L);
        log.info("根据角色Id查出的菜单是 :{}", FastJsonUtil.toJson(menuList) );
    }
}