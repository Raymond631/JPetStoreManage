package com.example.jpetstore_manage.POJO.ViewObject;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Raymond Li
 * @create 2023-03-21 0:15
 * @description 登录用1、3、5个属性，注册用1、3、4、5，修改密码用2、3、4、5
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserVO {
    private String username;
    private String oldPassword;
    private String password;
    private String rePassword;
    private String verificationCode;
}
