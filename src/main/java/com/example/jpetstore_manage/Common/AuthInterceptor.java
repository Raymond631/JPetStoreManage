package com.example.jpetstore_manage.Common;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * @author Raymond Li
 * @create 2023-03-20 18:47
 * @description 登录验证拦截器
 */
public class AuthInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object loginUser = request.getSession().getAttribute("loginUser");
        // 已登录
        if (loginUser != null) {
            return true;
        }
        // 未登录
        response.sendRedirect("/jpetstore/Error/NotLogin.html");
        return false;
    }
}
