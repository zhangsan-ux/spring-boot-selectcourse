package com.personal.course.controller;

import com.personal.course.annotation.PermissionCheck;
import com.personal.course.dto.sys.req.RoleQueryReq;
import com.personal.course.service.RoleService;
import com.personal.course.util.ViewUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**


@description 
@author cgc6828
@date 2020/5/5 16:33
*/
@Slf4j
@RequestMapping("/role")
@RestController
public class RoleController {

    @Resource
    private RoleService roleService;

   @PermissionCheck(value = "sys:role:list")
    @PostMapping("/listRolePage")
    public Object listRolePage (@RequestBody RoleQueryReq roleQueryReq){
        log.info("请求的参数是 ，{}",roleQueryReq);
     return ViewUtil.getPageInfo(roleService.roleListPage(roleQueryReq));
    }

}
