package com.lxc.elevateapi.aop;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.lxc.elevateapi.annotation.AuthCheck;
import com.lxc.elevateapi.common.ErrorCode;
import com.lxc.elevateapi.exception.BusinessException;
import com.lxc.elevateapi.model.entity.User;
import com.lxc.elevateapi.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


/**
 * 权限校验 AOP
 *
 * @author lxc
 */
@Aspect
@Component
public class AuthInterceptor {

    @Resource
    private UserService userService;

    /**
     * 执行拦截
     *
     * 这里注解为什么要配合doInterceptor方法使用呢？
     * 这是因为你可能希望在执行某些方法之前先进行权限校验，确保用户有权执行这些方法，这种权限校验的逻辑通常会用拦截器或者 AOP 来实现。
     *
     * @param joinPoint
     * @param authCheck
     * @return
     */
//    @annotation(authCheck) 表示这个方法 会拦截带有 authCheck 注解的方法。
//    使用了 @Around 注解，表示这个方法会在  被拦截的方法（这里说的拦截的方法就是注解中的两个属性方法mustRole()和anyRole（））
//    执行前后都会执行
    @Around("@annotation(authCheck)") // 表达式是针对切点配置（pointcut expression）的一种形式，用于匹配被特定注解标记的方法，
    public Object doInterceptor(ProceedingJoinPoint joinPoint, AuthCheck authCheck) throws Throwable {
//        将 authCheck 注解中的 anyRole 数组转换为流，过滤掉空白字符串后，转换为列表。
        List<String> anyRole = Arrays.stream(authCheck.anyRole()).filter(StringUtils::isNotBlank).collect(Collectors.toList());
//        获取 authCheck 注解中的 mustRole 字段。
        String mustRole = authCheck.mustRole();
//        通过 RequestContextHolder.currentRequestAttributes() 获取当前请求的属性，
        RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
//        并且从中获取 HttpServletRequest 对象。
        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
        // 当前登录用户
        User user = userService.getLoginUser(request);
        // 拥有任意权限即通过
//        检查用户是否拥有任意一种在 anyRole 中指定的角色，如果没有，则抛出权限异常。
        if (CollectionUtils.isNotEmpty(anyRole)) {
            String userRole = user.getUserRole();
            if (!anyRole.contains(userRole)) {
                throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
            }
        }
        // 必须有所有权限才通过
//        检查用户是否拥有在 mustRole 中指定的必须角色，如果不匹配，则抛出权限异常。
        if (StringUtils.isNotBlank(mustRole)) {
            String userRole = user.getUserRole();
            if (!mustRole.equals(userRole)) {
                throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
            }
        }
        // 通过权限校验，放行
        return joinPoint.proceed();
    }
}

