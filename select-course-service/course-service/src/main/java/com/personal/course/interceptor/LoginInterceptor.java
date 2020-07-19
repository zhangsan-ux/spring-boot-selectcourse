package com.personal.course.interceptor;

import com.personal.common.util.FastJsonUtil;
import com.personal.common.util.JwtUtil;
import com.personal.core.constant.CommonConstant;
import com.personal.core.enums.ResultEnum;
import com.personal.core.exception.BusinessException;
import com.personal.course.constant.RedisConstant;
import com.personal.course.constant.UserConstant;
import com.personal.course.dto.UserTokenDTO;
import com.personal.course.model.SysUser;
import com.personal.course.service.SysUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.TimeUnit;

/**
 * 登录拦截器
 *
 * @author Waldron Ye
 * @date 2019/6/2 13:29
 */
@Slf4j
public class LoginInterceptor implements HandlerInterceptor {


    @Resource
    private SysUserService sysUserService;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;


    @Value("${manager.config.isProd")
    private String isProd;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        this.checkToken(request, response);

        return true;
    }


    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {


        //当前请求进行处理之后，也就是Controller 方法调用之后执行.对应的视图之前

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        //当前请求进行处理之后，该方法将在整个请求结束之后，也就是在DispatcherServlet 渲染了对应的视图之后执行。对应的视图之后.
    }


    public void checkToken(HttpServletRequest request, HttpServletResponse response) throws BusinessException {
        log.info("经过拦截器");
        String requestToken = request.getHeader(CommonConstant.KEY_USER_TOKEN);
        if (StringUtils.isBlank(requestToken)) {
            BusinessException.err(ResultEnum.BUSINESS_ERROR, "请求的token为空");
        }
        String token = JwtUtil.resolveToken(requestToken);
        if (StringUtils.isBlank(token)) {
            BusinessException.err(ResultEnum.BUSINESS_ERROR, "解释token异常");
        }
        UserTokenDTO userTokenDTO = FastJsonUtil.toBean(token, UserTokenDTO.class);

        Long tokenRenewalSecond = userTokenDTO.getExpireDate().getTime() - System.currentTimeMillis();
        log.info("token剩下的毫秒数:tokenRenewalSecond={}", tokenRenewalSecond);
/*
        if (tokenRenewalSecond <= 0) {
            log.error("token已过期");
            BusinessException.err(ResultEnum.USER_STATUS_ERROR, "token已过期");
        }*/
        request.setAttribute(UserConstant.KEY_ROLE_ID, userTokenDTO.getRoleId());
        request.setAttribute(UserConstant.USER_ID,userTokenDTO.getUserId());
        request.setAttribute(UserConstant.USER_INFO,userTokenDTO);
        Long userId = userTokenDTO.getUserId();
        if (tokenRenewalSecond < 4 * 60 * 1000) {
            log.info("开始续命token");
            String key = RedisConstant.KEY_USER_TOKEN + userId;
            Long increment = stringRedisTemplate.opsForValue().increment(key);
            log.info("increment 的值是，{}", increment);
            if (increment == 1) {
                stringRedisTemplate.opsForValue().set(key, increment.toString(), 60, TimeUnit.SECONDS);
                SysUser sysUser = sysUserService.selectByPrimaryKey(userTokenDTO.getUserId());
                String newToken = sysUserService.getTokenByUser(sysUser);
                //将新生成的token放在请求响应头
                response.setHeader(CommonConstant.KEY_USER_TOKEN, newToken);
            }


        }
    }
}
