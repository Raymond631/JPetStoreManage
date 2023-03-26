package com.example.jpetstore_manage.Service.impl;


import com.example.jpetstore_manage.Mapper.UserMapper;
import com.example.jpetstore_manage.POJO.DataObject.UserMainDO;
import com.example.jpetstore_manage.POJO.ViewObject.Message;
import com.example.jpetstore_manage.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Raymond Li
 * @create 2023-03-20 16:33
 * @description
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public Message register(UserMainDO userMainDO) {
        UserMainDO userMainDo = userMapper.selectUserById(userMainDO);
        if (userMainDo != null) {
            return new Message(0, "用户名已存在");
        } else {
            int line = userMapper.insertUser(userMainDO);
            if (line == 1) {
                return new Message(1, "注册成功");
            } else {
                return new Message(0, "注册失败");
            }
        }
    }

    @Override
    public Message login(UserMainDO userMainDO) {
        UserMainDO userMainDo = userMapper.selectUserByIdAndPwd(userMainDO);
        if (userMainDo == null) {
            return new Message(0, "用户名和密码不匹配");
        } else {
            return new Message(1, "登录成功");
        }
    }

    @Override
    public Message changePassword(UserMainDO oldUserMainDO, UserMainDO newUserMainDO) {
        UserMainDO userMainDo = userMapper.selectUserByIdAndPwd(oldUserMainDO);
        if (userMainDo == null) {
            return new Message(0, "输入的原密码不正确");
        } else {
            int num = userMapper.updatePassword(newUserMainDO);
            if (num == 1) {
                return new Message(1, "修改密码成功");
            } else {
                return new Message(0, "修改密码失败");
            }
        }

    }

}
