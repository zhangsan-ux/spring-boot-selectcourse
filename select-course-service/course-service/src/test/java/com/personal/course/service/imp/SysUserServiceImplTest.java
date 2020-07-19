package com.personal.course.service.imp;

import com.personal.common.util.DateUtil;
import com.personal.common.util.FastJsonUtil;
import com.personal.core.dto.ResponseDataDTO;
import com.personal.core.util.ResultUtil;
import com.personal.course.dto.sys.req.UploadPhotoReq;
import com.personal.course.service.SysUserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author cgc6828
 * @description
 * @date 2020/5/25 21:30
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class SysUserServiceImplTest {

    @Resource
    private SysUserService sysUserService;

    @Test
    public void selectMenuByUserId() {
        List<String> s = sysUserService.selectMenuByUserId(1L);
        log.info("权限是：{}", FastJsonUtil.toJson(s));
    }

    @Test
    public void savePhoto() {
        String imagePath = "2222222222222";
        Long userId = 4L;
        UploadPhotoReq uploadPhotoReq = new UploadPhotoReq();
        uploadPhotoReq.setUserId(userId);
        uploadPhotoReq.setImagePath(imagePath);
        int i = sysUserService.savePhoto(uploadPhotoReq);
        if (i > 0) {
            log.info("成功");
        } else {
            log.info("失败");
        }
    }

    @Test
    public void test() {

        //1.
       List<HashMap<String ,Object >> list = new ArrayList<>();
       HashMap<String ,Object> hashMap = new HashMap<>();
       hashMap.put("accessPath","http://localhost/image/20200530/1590829181938.png");
       hashMap.put("imagePath","20200530/1590829181938.png");
        HashMap<String ,Object> hashMap1 = new HashMap<>();
        hashMap1.put("accessPath","http://localhost/image/20200530/1590829181938.png");
        hashMap1.put("imagePath","20200530/1590829181938.png");
        list.add(hashMap);
        list.add(hashMap1);
        //2.
        HashMap<String ,Object> hashMap3 = new HashMap<>();
        hashMap3.put("accessPath","http://localhost/image/20200530/1590829181938.png");
        HashMap<String ,Object> hashMap4 = new HashMap<>();
        hashMap4.put("accessPath","http://localhost/image/20200530/1590829181938.png");
        List<HashMap<String ,Object >> list1 = new ArrayList<>();
        list1.add(hashMap3);
        list1.add(hashMap4);
        //3.
        List<String> list2 = new ArrayList<>();
        list2.add("http://localhost/image/20200530/1590829181938.png");
        list2.add("http://localhost/image/20200530/1590829181938.png");

        ResponseDataDTO responseDataDTO = ResultUtil.getSuccessResult(list);
        log.info("第一种，{}",FastJsonUtil.toJson(responseDataDTO));

        HashMap<String,Object> hashMap6 = new HashMap<>();
        hashMap6.put("code",9999);
        hashMap6.put("data",list1);
        hashMap6.put("message","成功");
        log.info("第二种，{}",FastJsonUtil.toJson(hashMap6));
        HashMap<String,Object> hashMap7 = new HashMap<>();
        hashMap7.put("code",9999);
        hashMap7.put("data",list2);
        hashMap7.put("message","成功");
        log.info("第三种，{}",FastJsonUtil.toJson(hashMap7));
    }
}