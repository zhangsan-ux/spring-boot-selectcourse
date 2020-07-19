package com.personal.course.controller;

import com.personal.course.constant.UserConstant;
import com.personal.course.dto.UserTokenDTO;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author cgc6828
 * @description
 * @date 2020/4/16 21:55
 */
public abstract class BaseController {


    @Resource
    protected HttpServletRequest httpServletRequest;

    /**
     * 获取当前用户信息
     *
     * @return userTokenDTO
     */
    protected UserTokenDTO getLoginUserCourse() {
        return (UserTokenDTO) httpServletRequest.getAttribute(UserConstant.USER_INFO);
    }
}
