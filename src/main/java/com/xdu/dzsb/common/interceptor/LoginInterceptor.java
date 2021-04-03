package com.xdu.dzsb.common.interceptor;


import com.xdu.dzsb.service.base.RedisOperator;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author: wjy
 * @Date: 2021/3/1
 * @description: 拦截器
 */
@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    private RedisOperator redisOperator;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String session = request.getHeader("session");
        System.out.println(session);
        if (StringUtils.isNotBlank(session)) {
            return redisOperator.hasKey(session);
        }
        return false;
//        return true;
    }
}
