package com.wonder.bjutcats.config;

import com.wonder.bjutcats.interceptor.LoginCheckInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private LoginCheckInterceptor loginCheckInterceptor;

    // 注册拦截器
    public void addInterceptors(InterceptorRegistry registry){

        registry.addInterceptor(loginCheckInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/login")
                .excludePathPatterns("/catinfo/list")
                .excludePathPatterns("/catinfo/detail")
                .excludePathPatterns("catinfo/food")
                .excludePathPatterns("catinfo/tweet")
                .excludePathPatterns("/storage/image/**");

    }

}
