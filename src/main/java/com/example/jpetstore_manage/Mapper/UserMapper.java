package com.example.jpetstore_manage.Mapper;

import com.example.jpetstore_manage.POJO.DataObject.UserMainDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Raymond Li
 * @create 2023-03-20 16:35
 * @description
 */
@Mapper
public interface UserMapper {
    /**
     * 根据userId查找
     */
    public UserMainDO selectUserById(UserMainDO userMainDO);

    /**
     * 根据userId和password查找
     */
    public UserMainDO selectUserByIdAndPwd(UserMainDO userMainDO);

    /**
     * 插入新用户
     */
    public int insertUser(UserMainDO userMainDO);

    /**
     * 修改密码
     */
    public int updatePassword(UserMainDO userMainDO);
}
