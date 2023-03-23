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
     * 原密码校验(以oldUserMainDO为实参，调用selectUserByIdAndPwd),如果返回值不为null，则进行下一步，否则返回错误信息（原密码错误）
     * 修改用户表的密码（以newUserMainDO为实参，调用updatePassword）
     */
<<<<<<< Updated upstream
    public Message changePassword(UserMainDO oldUserMainDO, UserMainDO newUserMainDO);
=======
    public Message changePassword(UserMainDO oldUserMainDO,UserMainDO newUserMainDO);
>>>>>>> Stashed changes
}
