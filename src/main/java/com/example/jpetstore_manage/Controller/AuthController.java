package com.example.jpetstore_manage.Controller;

import com.example.jpetstore_manage.POJO.ViewObject.CommonResponse;
import com.example.jpetstore_manage.Service.AuthService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import me.zhyd.oauth.model.AuthCallback;
import me.zhyd.oauth.model.AuthResponse;
import me.zhyd.oauth.model.AuthUser;
import me.zhyd.oauth.request.AuthRequest;
import me.zhyd.oauth.utils.AuthStateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * @author Raymond Li
 * @create 2023-03-23 20:19
 * @description 第三方授权登录类
 */
@Slf4j
@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    /**
     * 跳往授权登录页面
     */
    @RequestMapping("/render/{source}")
    public void renderAuth(@PathVariable("source") String source, HttpServletResponse response) throws IOException {
        AuthRequest authRequest = authService.getAuthRequest(source);
        response.sendRedirect(authRequest.authorize(AuthStateUtils.createState()));
    }

    /**
     * 回调，取得授权码（callback）
     * 用授权码去获取令牌，再用令牌去获取用户信息（response）
     */
    @RequestMapping("/callback/{source}")
    public Object login(@PathVariable("source") String source, AuthCallback callback) throws IOException {
        AuthRequest authRequest = authService.getAuthRequest(source);
        AuthResponse<AuthUser> response = authRequest.login(callback);
        System.out.println(response.getData().getUsername());
        if (response.ok()) {
            String uuid = response.getData().getUuid();
            System.out.println(uuid);
            System.out.println("-----------");
            return null;
        } else {
            return new CommonResponse(0, response.getMsg());
        }
    }
}
