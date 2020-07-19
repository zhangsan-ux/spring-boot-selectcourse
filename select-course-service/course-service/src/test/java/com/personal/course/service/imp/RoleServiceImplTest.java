package com.personal.course.service.imp;

import com.personal.common.util.FastJsonUtil;
import com.personal.course.dao.RoleDao;
import com.personal.course.model.Role;
import com.personal.course.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @author cgc6828
 * @description
 * @date 2020/5/5 11:28
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class RoleServiceImplTest {

    @Autowired
    private RoleService roleService;

    @Resource
    private RoleDao roleDao;
    @Test
    public void insertRole() {
        Role role = new Role();

        role.setRoleName("老师");
        role.setRoleRemark("你好！！");

        int i = roleService.insertRole(role);

        if (i>0) {
            log.info("插入成功");
        }else {
            log.info("插入失败");
        }
    }

    @Test
    public void selectRoleByRoleName (){
        String roleName = "教师";
        Role roleTemp  =roleService.selectRoleByRoleName(roleName);

            log.info("查出的角色是 {}",roleTemp);
    }
    @Test
    public void updateRoleByRoleId(){
        Role role = new Role() ;
        role.setRoleId(4L);
        role.setRoleName("老师呜呜呜呜");
        role.setRoleRemark("wuwuwuuwu");
        int i =  roleService.updateRoleByRoleId(role);
        if(i == 1){
            log.info("跟新了 {} 条数据",i );
        }

    }

    @Test
    public void  selectMenuByRoleIdTest(){
        Long roleId = 2L;
     List<String> permissionList = roleDao.selectMenuByRoleId(roleId);
     log.info("权限是={}", FastJsonUtil.toJson(permissionList));
    }
}