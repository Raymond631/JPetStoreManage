package com.example.jpetstore_manage.Common;

import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Raymond Li
 * @create 2023-03-20 18:45
 * @description 拦截器配置类
 */
public class AuthConfig implements WebMvcConfigurer {
    /**
     * addPathPatterns为拦截路径
     * excludePathPatterns为从拦截路径中排除的路径
     * 第一行：静态资源
     * 第二行：开发接口
     * 第三行：免登录界面
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AuthInterceptor()).addPathPatterns("/**")
                .excludePathPatterns("/image/**", "/css/**", "/js/**",
                        "/verificationCode",
                        "/", "/Error/NotLogin.html");
    }
}
