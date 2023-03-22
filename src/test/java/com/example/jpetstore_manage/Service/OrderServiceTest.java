package com.example.jpetstore_manage.Service;

import com.example.jpetstore_manage.Mapper.OrderMapper;
import com.example.jpetstore_manage.POJO.DataObject.OrderItemDO;
import com.example.jpetstore_manage.POJO.ViewObject.Message;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Raymond Li
 * @create 2023-03-21 22:22
 * @description
 */
@RunWith(SpringRunner.class)
@SpringBootTest
class OrderServiceTest {
    /**
     * 调用orderService中的方法进行测试
     */
    @Autowired
    private OrderService orderService;

    /**
     * Mockito模拟测试
     * 测试service时，如果mapper层还没写完，可以用Mockito生成模拟返回值
     */
//    @MockBean
//    private OrderMapper orderMapper;

    @Test
    void getOrderItemList() {
        List<OrderItemDO> orderItemDOS=new ArrayList<>();
        orderItemDOS=orderService.getOrderItemList("csu001");
        System.out.println(orderItemDOS);
    }

    @Test
    void ship() {
        Message message=orderService.ship(9,"csu001");
        System.out.println(message);
    }
}