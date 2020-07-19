package com.personal.course.controller;

import com.alibaba.fastjson.JSONObject;
import com.personal.common.util.FastJsonUtil;
import com.personal.common.util.HttpClientUtil;
import com.personal.course.dto.sys.req.UserLoginReq;

import java.util.HashMap;
import java.util.Map;

/**


@description 
@author cgc6828
@date 2020/7/9 20:58
*/
public class LoginImitate {

    public static void  main (String[] args) {
        String url = "http://localhost/select-course/user/login";
        UserLoginReq userLoginReq  = new UserLoginReq();
        userLoginReq.setBusinessNumber("16100529208");
        userLoginReq.setUserPassword("123456");
        Map<String, Object> map = FastJsonUtil.jsonStrToMap(FastJsonUtil.toJson(userLoginReq));
        JSONObject jsonObject =  new JSONObject(map);
        String s= HttpClientUtil.doPost(url,jsonObject);
        System.out.println(s);
    }
}


