package com.personal.course.controller;

import com.personal.common.util.Md5Util;
import com.personal.course.model.SysMenu;
import com.personal.course.model.SysUser;
import com.personal.course.service.SysUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author cgc6828
 * @className UserControllerTest
 * @description
 * @date 2020/4/11 16:36
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class UserControllerTest {


    @Resource
    private SysUserService sysUserService;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    public void cretePwd() {
        String pwd1 = "111111";
        String salt1 = "1234";

        String pwd2 = Md5Util.md5To32Encrypt(pwd1);
        String salt2 = Md5Util.md5To32Encrypt(salt1);
        String pwd = pwd2 + salt2;
        log.info("生成的密码是 :{}", pwd);

    }

    @Test
    public void pwd1() {
        String string1 = "e10adc3949ba59abbe56e057f20f883e81dc9bdb52d04dc20036dbd8313ed055";
        String salt = "1234";
        String pad = "123456";
        if (string1.equals(Md5Util.md5To32Encrypt(pad) + Md5Util.md5To32Encrypt(salt))) {
            log.info("密码正确");
        } else {
            log.info("密码错误");
        }

    }

    @Test
    public void createPwd() {
        String pwd = "123456";
        String salt = "e10adc3949ba59abbe56e057f20f883e81dc9bdb52d04dc20036dbd8313ed055";
        String pwd2 = Md5Util.md5To32Encrypt(pwd) + Md5Util.md5To32Encrypt(salt);
        log.info("密码为:pwd2={}", pwd2);
    }


    @Test
    public void stringTo6() {
        String s = "440921199608164271";
        String s1 = s.substring(s.length() - 6, s.length());
        log.info("后六位是:{}", s1);
    }

    @Test
    public void valid() {
//        try {
//            AddStudentReq2 addStudentReq = new AddStudentReq2();
//            addStudentReq.setMobile("17879552120");
//            ValidatorUtil.validateObject(addStudentReq);
//            log.info("校验通过");
//        } catch (BusinessException e) {
//            log.error("校验不通过:{}", e.getMsg());
//        }
        String str = "";
        if (StringUtils.isEmpty(str)) {
            log.info("为空");
        } else {
            log.info("不为空");
        }

    }

    @Test
    public void getToken (){
        SysUser sysUser = new SysUser();

        Long userId = 5L;
        sysUser.setUserId(userId);
        String  token =  sysUserService.getTokenByUser(sysUser);
        log.info("生成的token是，{}",token);
    }

   @Test
    public void redisTest(){
        String key = "name";
       stringRedisTemplate.opsForValue().increment(key, 1L);
       String value = stringRedisTemplate.opsForValue().get(key);
       log.info("value={}", value);
   }


   @Test
   public void permissionTest(){
        Long userId=  1L;
        List<String> sysMenuList =    sysUserService.selectMenuByUserId(userId);
         List<String> permissionList  = new ArrayList<>();
         for (String object :sysMenuList){

     if (object!=null) {
         if (!"".equals(object)) {
             permissionList.add(object);

         }
     }
         }
        log.info("权限是 = {}",permissionList);
   }

}