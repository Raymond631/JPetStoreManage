package com.example.jpetstore_manage.POJO.MapStruct;

import com.example.jpetstore_manage.POJO.ViewObject.UserVO;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Raymond Li
 * @create 2023-03-21 10:45
 * @description
 */
@RunWith(SpringRunner.class)
@SpringBootTest
class UserMappingTest {
    @Autowired
    private UserMapping userMapping;

    @Test
    void userVOtoUserMainDO() {
        UserVO userVO = new UserVO("abc", "123", "456", "456", "000");
        System.out.println(userMapping.UserVOtoUserMainDO(userVO));
    }
}