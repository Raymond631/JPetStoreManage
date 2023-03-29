package com.example.jpetstore_manage.Service.impl;

import com.example.jpetstore_manage.Service.AuthService;
import me.zhyd.oauth.config.AuthConfig;
import me.zhyd.oauth.enums.scope.AuthWeiboScope;
import me.zhyd.oauth.exception.AuthException;
import me.zhyd.oauth.request.AuthAlipayRequest;
import me.zhyd.oauth.request.AuthQqRequest;
import me.zhyd.oauth.request.AuthRequest;
import me.zhyd.oauth.request.AuthWeiboRequest;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

/**
 * @author Raymond Li
 * @create 2023-03-28 20:37
 * @description
 */
@Service
public class AuthServiceImpl implements AuthService {
    /**
     * 创建请求
     */
    @Override
    public AuthRequest getAuthRequest(String source) throws IOException {
        AuthRequest authRequest;
        switch (source.toLowerCase()) {
            case "alipay":
                authRequest = new AuthAlipayRequest(AuthConfig.builder()
                        .clientId("2021003184678755")
                        .clientSecret(getKey("应用私钥RSA2048-敏感数据，请妥善保管.txt"))
                        .alipayPublicKey(getKey("alipayPublicKey_RSA2.txt"))
                        .redirectUri("http://192.168.3.107:8888/jpetstore/auth/callback/alipay")
                        .build());
                break;
            case "qq":
                authRequest = new AuthQqRequest(AuthConfig.builder()
                        .clientId("102043255")
                        .clientSecret("c6MN9gz3sSntkMjr")
                        .redirectUri("http://localhost:8888/jpetstore/auth/callback/qq")
                        .build());
                break;
            case "weibo":
                authRequest = new AuthWeiboRequest(AuthConfig.builder()
                        .clientId("3582380050")
                        .clientSecret("cdbafc2c9282bea42de2799089416e5b")
                        .redirectUri("http://192.168.3.107:8888/jpetstore/auth/callback/weibo")
                        .scopes(Arrays.asList(
                                AuthWeiboScope.EMAIL.getScope(),
                                AuthWeiboScope.FRIENDSHIPS_GROUPS_READ.getScope(),
                                AuthWeiboScope.STATUSES_TO_ME_READ.getScope()
                        ))
                        .build());
                break;
            default:
                throw new AuthException("未获取到有效的Auth配置");
        }
        return authRequest;
    }

    /**
     * 读取支付宝私钥
     */
    @Override
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
