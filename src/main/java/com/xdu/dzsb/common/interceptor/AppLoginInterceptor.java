package com.xdu.dzsb.common.interceptor;


import com.xdu.dzsb.service.base.RedisOperator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class AppLoginInterceptor implements HandlerInterceptor {
    @Autowired
    private RedisOperator redisOperator;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String session = request.getParameter("session");
        String username = request.getHeader("userName");
        Boolean b1 = redisOperator.hasKey(username);
        if(!b1)
            return false;
        String value = redisOperator.getValue(username);
        boolean b2 = value.equals(session);
        return b2;
    }

}
