package com.xdu.dzsb.common.configuration;

import com.xdu.dzsb.common.interceptor.AppLoginInterceptor;
import com.xdu.dzsb.common.interceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @author: wjy
 * @Date: 2021/3/1
 * @description: 拦截器适配器配置
 */
@Configuration
public class WebConfigurer extends WebMvcConfigurationSupport {

    @Autowired
    private LoginInterceptor loginInterceptor;

    @Autowired
    private AppLoginInterceptor appLoginInterceptor;

    @Override
    // 注册拦截器
    public void addInterceptors(InterceptorRegistry registry) {

        // addPathPatterns("/**") 表示拦截所有的请求。
        // excludePathPatterns("/user/login"); //表示登录接口不拦截。
//        registry.addInterceptor(appLoginInterceptor).addPathPatterns("/app/**").excludePathPatterns("/app/user/login", "/app/user/register");
//        registry.addInterceptor(loginInterceptor).addPathPatterns("/user/**", "/exercise/**").excludePathPatterns("/user/login", "/epidemic", "/user/test");
        super.addInterceptors(registry);
    }
}