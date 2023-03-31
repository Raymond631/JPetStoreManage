package com.example.jpetstore_manage.Mapper;

import com.example.jpetstore_manage.POJO.DataObject.UserInfoDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author Raymond Li
 * @create 2023-03-31 23:08
 * @description
 */
@Mapper
public interface AdminMapper {

    List<UserInfoDO> selectAllUser();

    void deleteUser(int userId);

    void updateUser(UserInfoDO userInfoDO);
}
