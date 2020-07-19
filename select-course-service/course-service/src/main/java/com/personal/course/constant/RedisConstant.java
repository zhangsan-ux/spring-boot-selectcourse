package com.personal.course.constant;

/**
 * @author cgc6828
 * @className RedisConstant
 * @description TODO
 * @date {DATE}{TIME}
 */
public class RedisConstant {
    /**
     * redis中token的key
     */
    public static final String KEY_USER_TOKEN = "user:info:";

    /**
     * 用户token续命的key
     */
    public static final String KEY_USER_LOGIN = "user:login:";

    /**
     * 生成权限列表的Key值
     */
    public static final String PERMISSION_KEY = "permission:list:";

    /**
     * 登录时 将菜单加载在Redis的Key
     */
    public static  final String MENU_KEY = "menu:list:";

}
