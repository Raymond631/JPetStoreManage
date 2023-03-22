package com.example.jpetstore_manage.Mapper;

import com.example.jpetstore_manage.POJO.DataObject.OrderItemDO;
import com.example.jpetstore_manage.POJO.DataObject.OrderMainDO;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Raymond Li
 * @create 2023-03-21 22:23
 * @description
 */
@RunWith(SpringRunner.class)
@SpringBootTest
class OrderMapperTest {
    @Autowired
    public OrderMapper orderMapper;


    @Test
    void selectOrderItemBySupplier() {
        List<OrderItemDO> orderItemDO=new ArrayList<>();
        orderItemDO=orderMapper.selectOrderItemBySupplier("csu001");
        System.out.println(orderItemDO);
    }

    @Test
    void selectOrderMainByOrderId() {
        OrderMainDO orderMainDO=new OrderMainDO();
        orderMainDO=  orderMapper.selectOrderMainByOrderId(6L);
        System.out.println(orderMainDO);
    }


    @Test
    void updateOrderStatus() {
        System.out.println(orderMapper.updateOrderStatus(9,"已发货","csu001"));
    }
}