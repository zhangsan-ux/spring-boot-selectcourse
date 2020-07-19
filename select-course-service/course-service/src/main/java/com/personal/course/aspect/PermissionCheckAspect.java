package com.personal.course.aspect;

import com.personal.common.util.FastJsonUtil;
import com.personal.core.enums.ResultEnum;
import com.personal.core.exception.BusinessException;
import com.personal.course.annotation.PermissionCheck;
import com.personal.course.constant.RedisConstant;
import com.personal.course.constant.UserConstant;
import com.personal.course.service.SysUserService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Objects;

/**
 * @author cgc6828
 * @description
 * @date 2020/5/21 16:55
 */
@Aspect
@Slf4j
public class PermissionCheckAspect {


    @Resource
    private StringRedisTemplate stringRedisTemplate;


    /**
     * 切入点表达式决定了用注解方式的方法切还是针对某个路径下的所有类和方法进行切，方法必须是返回void类型
     */
    @Pointcut(value = "@annotation(com.personal.course.annotation.PermissionCheck)")
    private void permissionCheckCut() {
    }

    ;

    //定义了切面的处理逻辑。即方法上加了@PermissionCheck
    @Around("permissionCheckCut()")

    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        log.info("====================进入AOP============================");
        //1.记录日志信息
        Signature signature = pjp.getSignature();
        String className = pjp.getTarget().getClass().getSimpleName();
        String methodName = signature.getName();
        log.info("className:{},methodName:{}", className, methodName);

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        Object roleId = request.getAttribute(UserConstant.KEY_ROLE_ID);
        String permissionKey = RedisConstant.PERMISSION_KEY + roleId;
        String permissionStr = this.stringRedisTemplate.opsForValue().get(permissionKey);
        List<String> permissionList = FastJsonUtil.toList(permissionStr, String.class);

        //2.角色权限校验
        MethodSignature methodSignature = (MethodSignature) signature;
        Method targetMethod = methodSignature.getMethod();
        //获取方法上注解中表明的权限
        PermissionCheck permission = targetMethod.getAnnotation(PermissionCheck.class);
        String[] permsArr = permission.value();
        log.info("当前接口请求的用户角色权限是:{}", permsArr);
        //接口允许的角色

        //打印管理员权限
        log.info("拥有的权限:" + permissionList);
        //将注解上标明的权限与查出来的权限进行比对
        boolean flag = false;
        if (permissionList != null) {
            for (String perms : permsArr) {
                for (String permissionStrNew : permissionList) {
                    if (Objects.equals(perms, permissionStrNew)) {
                        flag = true;
                        break;
                    }
                }
            }
        }
        if (!flag) {
            log.info("暂无权限:roleId={}", roleId);
            //抛异常
            BusinessException.err(ResultEnum.BUSINESS_ERROR, "暂无访问权限");
        }
        log.info("AOP权限角色校验通过，进入业务层处理！");
        //3.执行业务逻辑，放行
        return pjp.proceed();
    }
}
