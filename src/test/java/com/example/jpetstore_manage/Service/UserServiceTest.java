package com.example.jpetstore_manage.Service;

import com.example.jpetstore_manage.Mapper.UserMapper;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Raymond Li
 * @create 2023-03-21 22:23
 * @description
 */
@RunWith(SpringRunner.class)
@SpringBootTest
class UserServiceTest {
    /**
     * 调用userService中的方法进行测试
     */
    @Autowired
    private UserService userService;

    /**
     * Mockito模拟测试
     * 测试service时，如果mapper层还没写完，可以用Mockito生成模拟返回值
     */
    @MockBean
    private UserMapper userMapper;
}