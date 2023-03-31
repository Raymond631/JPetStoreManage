package com.example.jpetstore_manage.Service.impl;

import com.example.jpetstore_manage.Mapper.AdminMapper;
import com.example.jpetstore_manage.POJO.DataObject.UserInfoDO;
import com.example.jpetstore_manage.Service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Raymond Li
 * @create 2023-03-31 23:06
 * @description
 */
@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminMapper adminMapper;

    @Override
    public List<UserInfoDO> getAllUser() {
        return adminMapper.selectAllUser();
    }

    @Override
    public void removeUser(int userId) {
        adminMapper.deleteUser(userId);
    }

    @Override
    public void updateUserInfo(UserInfoDO userInfoDO) {
        adminMapper.updateUser(userInfoDO);
    }
}
