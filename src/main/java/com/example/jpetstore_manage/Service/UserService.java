package com.example.jpetstore_manage.Service;

import com.example.jpetstore_manage.POJO.DataObject.UserMainDO;
import com.example.jpetstore_manage.POJO.ViewObject.Message;

/**
 * @author Raymond Li
 * @create 2023-03-20 16:32
 * @description
 */
public interface UserService {

    /**
     * 用户名查重
     * 注册，插入用户表
     */
    public Message register(UserMainDO userMainDO);

    /**
     * 登陆，查用户名和密码是否匹配
     */
    public Message login(UserMainDO userMainDO);

    /**
     * 原密码校验
     * 修改用户表的密码
     */
    public Message changePassword(UserMainDO userMainDO);
}
