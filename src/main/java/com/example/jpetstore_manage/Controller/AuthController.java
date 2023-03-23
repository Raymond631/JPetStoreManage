package com.example.jpetstore_manage.Controller;

import com.alibaba.fastjson.JSON;
import com.example.jpetstore_manage.POJO.ViewObject.Message;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import me.zhyd.oauth.config.AuthConfig;
import me.zhyd.oauth.model.AuthCallback;
import me.zhyd.oauth.model.AuthResponse;
import me.zhyd.oauth.model.AuthUser;
import me.zhyd.oauth.request.AuthAlipayRequest;
import me.zhyd.oauth.request.AuthRequest;
import me.zhyd.oauth.utils.AuthStateUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.charset.StandardCharsets;

/**
 * @author Raymond Li
 * @create 2023-03-23 20:19
 * @description 第三方授权登录类
 */
@Slf4j
@RestController
@RequestMapping("/auth")
public class AuthController {
    /**
     * 跳往授权登录页面
     */
    @RequestMapping("/render")
    public void renderAuth(HttpServletResponse response) throws IOException {
        AuthRequest authRequest = getAuthRequest();
        response.sendRedirect(authRequest.authorize(AuthStateUtils.createState()));
    }

    /**
     * 回调，取得授权码（callback）
     * 用授权码去获取令牌，再用令牌去获取用户信息（response）
     */
    @RequestMapping("/callback")
    public Object login(AuthCallback callback) throws IOException {
        AuthRequest authRequest = getAuthRequest();
        AuthResponse<AuthUser> response = authRequest.login(callback);

        System.out.println(JSON.toJSONString(response));
        if (response.ok()) {
            // TODO 授权登录成功，后续如何处理？
            return null;
        } else {
            return new Message(0, response.getMsg());
        }
    }

    /**
     * 创建请求
     */
    private AuthRequest getAuthRequest() throws IOException {
        return new AuthAlipayRequest(AuthConfig.builder()
                .clientId("2021003184678755")
                .clientSecret(getKey("应用私钥RSA2048-敏感数据，请妥善保管.txt"))
                .alipayPublicKey(getKey("alipayPublicKey_RSA2.txt"))
                .redirectUri("http://192.168.3.107:8888/jpetstore/auth/callback")
                .build());
    }

    /**
     * 读取私钥
     */
    public String getKey(String fileName) throws IOException {
        StringBuilder key = new StringBuilder();
        try (Reader reader = new FileReader(System.getProperty("user.home") + "/Documents/支付宝开放平台密钥工具/密钥20230323104847/" + fileName, StandardCharsets.UTF_8)) {
            while (true) {
                // 反复调用read()方法，直到返回-1
                int n = reader.read();
                if (n == -1) {
                    break;
                } else {
                    key.append((char) n);
                }
            }
        }
        return key.toString();
    }
}
