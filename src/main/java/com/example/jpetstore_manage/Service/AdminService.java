package com.example.jpetstore_manage.Service;

import com.example.jpetstore_manage.POJO.DataObject.UserInfoDO;

import java.util.List;

/**
 * @author Raymond Li
 * @create 2023-03-31 23:05
 * @description
 */
public interface AdminService {
    List<UserInfoDO> getAllUser();

    void removeUser(int userId);

    void updateUserInfo(UserInfoDO userInfoDO);
}
