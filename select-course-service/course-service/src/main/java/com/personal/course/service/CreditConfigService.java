package com.personal.course.service;

import com.personal.course.model.CreditConfig;

import java.lang.module.Configuration;

/**
 * @author cgc6828
 * @description
 * @date 2020/4/15 10:57
 */
public interface CreditConfigService  {

    /**
     * 根据id查找最低，最高可以选的学分
     * @return  最低，最高可以选的学分
     */
    CreditConfig selectById();
}
