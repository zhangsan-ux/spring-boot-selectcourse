package com.personal.course.controller;

import com.personal.core.enums.ResultEnum;
import com.personal.core.util.ResultUtil;
import com.personal.course.annotation.PermissionCheck;
import com.personal.course.dto.sys.req.RoleMenuReq;
import com.personal.course.dto.sys.resp.SysMenuResp;
import com.personal.course.model.Role;
import com.personal.course.model.RoleMenu;
import com.personal.course.model.SysMenu;
import com.personal.course.service.RoleService;
import com.personal.course.service.SysMenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.*;

/**
 * @author cgc6828
 * @description 菜单接口
 * @date 2020/5/4 18:36
 */
@RestController
@RequestMapping("/menu")
@Slf4j
public class MenuController {

    @Resource
    private SysMenuService sysMenuService;

    @Resource
    private RoleService roleService;

   @PermissionCheck(value = {"sys:role:list"})
    @GetMapping("/selectAllMenuByRoleId")
    public Object selectAllMenuByRoleId(Long roleId) {
        log.info("请求的参数是 : {}", roleId);
        if (roleId == null) {
            Map<String, Object> dateMap = new HashMap<>();
            List<SysMenu> sysMenus = sysMenuService.selectAllMenu();
            List<SysMenuResp> sysMenuRespList = new ArrayList<>();

            SysMenuResp sysMenuResp1;
            for (SysMenu sysMenu : sysMenus) {
                sysMenuResp1 = new SysMenuResp();
                sysMenuResp1.setName(sysMenu.getName());
                sysMenuResp1.setId(sysMenu.getMenuId());
                sysMenuResp1.setPId(sysMenu.getParentId());
                sysMenuRespList.add(sysMenuResp1);
            }

            dateMap.put("menus", sysMenuRespList);
            return ResultUtil.getSuccessResult(dateMap);
        }
        if (roleId == 0 || roleId < 0) {
            return ResultUtil.getResult(ResultEnum.PARAMETER_ERROR.getCode(), "角色Id非法");
        }

        //根据角色Id 查出 指定 角色的信息
        Role role = sysMenuService.getMenuInfoByRoleId(roleId);

        //根据 角色Id 查出 角色中间表的 实体类
        List<RoleMenu> roleMenuList = sysMenuService.listAllMenuByRoleId(roleId);

        //查出菜单表的所有菜单
        List<SysMenu> sysMenusList = sysMenuService.selectAllMenu();
        List<SysMenuResp> sysMenuRespList = new ArrayList<>();

        SysMenuResp sysMenuResp1;
        //菜单列表数据组装
        for (SysMenu sysMenu : sysMenusList) {
            sysMenuResp1 = new SysMenuResp();
            sysMenuResp1.setName(sysMenu.getName());
            sysMenuResp1.setId(sysMenu.getMenuId());
            sysMenuResp1.setPId(sysMenu.getParentId());
            boolean flag = false;
            for (RoleMenu roleMenu : roleMenuList) {
                if (Objects.equals(roleMenu.getMenuId(), sysMenu.getMenuId())) {
                    flag = true;
                    break;
                }
            }
            sysMenuResp1.setChecked(flag);
            sysMenuRespList.add(sysMenuResp1);
        }

        Map<String, Object> dateMap = new HashMap<>();
        dateMap.put("menus", sysMenuRespList);
        dateMap.put("roleName", role.getRoleName());
        dateMap.put("roleRemark", role.getRoleRemark());
        return ResultUtil.getSuccessResult(dateMap);
    }
    @PermissionCheck(value = {"sys:role:add","sys:role:edit"})
    @PostMapping("/saveMenusByRoleId")
    public Object saveMenusByRoleId(@RequestBody RoleMenuReq roleMenuReq) {
        log.info("请求的参数是 : {}", roleMenuReq);
        //插入
        if (roleMenuReq.getRoleId() == null) {
            Role roleInsert = new Role();
            roleInsert.setRoleName(roleMenuReq.getRoleName());
            roleInsert.setRoleRemark(roleMenuReq.getRoleRemark());
            roleService.insertRole(roleInsert);
            log.info("插入成功:roleId={}", roleInsert.getRoleId());
            sysMenuService.insertMenusByRoleId(roleMenuReq.getMenuIdList(), roleInsert.getRoleId());
            log.info("插入菜单成功");
            return ResultUtil.getResult(ResultEnum.SUCCESS);
        }
        //1、校验 roleId 判断角色 是否存在
        Role roleExit = roleService.selectRoleByRoleId(roleMenuReq.getRoleId());
        if (roleExit == null) {
            return ResultUtil.getResult(ResultEnum.PARAMETER_ERROR.getCode(), "角色Id非法");
        }
        //1、更新角色信息
        Role role = new Role();
        role.setRoleId(roleMenuReq.getRoleId());
        role.setRoleRemark(roleMenuReq.getRoleRemark());
        roleService.updateRoleByRoleId(role);
        log.info("跟新角色成功");
        //3、删除角色菜单中间表信息
        sysMenuService.deleteAllMenuByRoleId(roleMenuReq.getRoleId());

        //4、批量插入角色菜单信息
        sysMenuService.insertMenusByRoleId(roleMenuReq.getMenuIdList(), roleMenuReq.getRoleId());
        log.info("跟新菜单成功");
        return ResultUtil.getResult(ResultEnum.SUCCESS);
    }

}
