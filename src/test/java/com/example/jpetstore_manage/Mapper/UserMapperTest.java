package com.example.jpetstore_manage.Mapper;

import com.example.jpetstore_manage.POJO.DataObject.UserMainDO;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Raymond Li
 * @create 2023-03-21 22:23
 * @description
 */
@RunWith(SpringRunner.class)
@SpringBootTest
class UserMapperTest {
    @Autowired
    private UserMapper userMapper;

    @Test
    void selectUserById() {
        UserMainDO userMainDO = new UserMainDO();
        userMainDO.setUserId("123");
        System.out.println(userMapper.selectUserById(userMainDO));
    }

    @Test
    void selectUserByIdAndPwd() {
        UserMainDO userMainDO = new UserMainDO("123","123");
        System.out.println(userMapper.selectUserByIdAndPwd(userMainDO));
    }

    @Test
    void insertUser() {
        UserMainDO userMainDO = new UserMainDO("aaa","aaa");
        System.out.println(userMapper.insertUser(userMainDO));

    }

    @Test
    void updatePassword() {
        UserMainDO userMainDO = new UserMainDO("123","456");
        System.out.println(userMapper.updatePassword(userMainDO));
    }
}