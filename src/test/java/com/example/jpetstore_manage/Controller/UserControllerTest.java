package com.example.jpetstore_manage.Controller;

import com.example.jpetstore_manage.POJO.DataObject.UserMainDO;
import com.example.jpetstore_manage.Service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.DigestUtils;

/**
 * @author Raymond Li
 * @create 2023-03-21 22:46
 * @description
 */
@RunWith(SpringRunner.class)
@SpringBootTest
class UserControllerTest {
    /**
     * Mockito模拟测试
     * 测试controller时，如果service层还没写完，可以用Mockito生成模拟返回值
     */
    @MockBean
    private UserService userService;

    @Test
    void register() {
        UserMainDO userMainDO = new UserMainDO("123", "MD5AES");
        System.out.println(DigestUtils.md5DigestAsHex(userMainDO.getPassword().getBytes()));
    }

    @Test
    void login() {
    }

    @Test
    void signOut() {
    }

    @Test
    void changePassword() {
    }
}