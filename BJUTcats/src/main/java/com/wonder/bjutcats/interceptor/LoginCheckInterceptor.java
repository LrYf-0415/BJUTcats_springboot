package com.wonder.bjutcats.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.wonder.bjutcats.util.JwtUtil;
import com.wonder.bjutcats.util.Result;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
@Component
public class LoginCheckInterceptor implements HandlerInterceptor {

    // 使用拦截器实现登录校验
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        // 获取请求头中的令牌
        String jwt = request.getHeader("token");

        // 判断header是否有令牌
        if(!StringUtils.hasLength(jwt)){
            // 请求头为空，拦截
            Result error = Result.error("not_login");
            String errorMsg = JSONObject.toJSONString(error);
            response.getWriter().write(errorMsg);
            return false;
        }

        // 判断header令牌是否合法
        try{
            JwtUtil.parseJWT(jwt);
        }
        catch(Exception e){
            e.printStackTrace();
            Result error = Result.error("not_login");
            String errorMsg = JSONObject.toJSONString(error);
            response.getWriter().write(errorMsg);
            return false;
        }

        // 若令牌合法则不拦截
        return true;
    }

}
