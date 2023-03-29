package com.example.jpetstore_manage.Service;

import me.zhyd.oauth.request.AuthRequest;

import java.io.IOException;

/**
 * @author Raymond Li
 * @create 2023-03-28 20:36
 * @description
 */
public interface AuthService {
    public AuthRequest getAuthRequest(String source) throws IOException;

    public String getKey(String fileName) throws IOException;
}
