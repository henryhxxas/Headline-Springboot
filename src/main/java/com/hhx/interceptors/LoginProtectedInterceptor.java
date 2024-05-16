package com.hhx.interceptors;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hhx.utils.JwtHelper;
import com.hhx.utils.Result;
import com.hhx.utils.ResultCodeEnum;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * @Author: hhx
 * @Date: 2024/5/15 16:48
 * @Description: TODO 登录包含拦截器，检查请求头是否包含有效token 有效：放行；无效：返回504
 * @Version: 1.0
 */
@Component
public class LoginProtectedInterceptor implements HandlerInterceptor {
    @Autowired
    private JwtHelper jwtHelper;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //从请求头中获取token
        String token = request.getHeader("token");
        //检查是否有效
        boolean expiration = jwtHelper.isExpiration(token);
        if(!expiration){
            //有效放行
            return true;
        }
        //无效返回504状态的json
        Result result = Result.build(null, ResultCodeEnum.NOTLOGIN);
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(result);
        response.getWriter().print(json);
        return false;
    }
}
