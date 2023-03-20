package com.example.jpetstore_manage.POJO.ViewObject;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Raymond Li
 * @create 2023-03-20 23:24
 * @description 注册视图类
 * 用户名
 * 密码
 * 重复密码
 * 验证码
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterVO {
    private String username;
    private String password;
    private String rePassword;
    private String vCode;
}
