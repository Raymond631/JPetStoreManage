package com.example.jpetstore_manage.POJO.MapStruct;

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
 * @create 2023-03-21 11:37
 * @description
 */
@RunWith(SpringRunner.class)
@SpringBootTest
class OrderMappingTest {
    @Autowired
    private OrderMapping orderMapping;

    @Test
    void orderItemDOtoOrderVO() {
        OrderItemDO orderItemDO = new OrderItemDO();
        orderItemDO.setOrderItemId(1);
        orderItemDO.setWhetherShip("已发货");
        orderItemDO.setItemQuantity(2);
        orderItemDO.setProductNameChinese("哈士奇");
        orderItemDO.setItemSpecification("成年雄性");

        OrderMainDO orderMainDO = new OrderMainDO();
        orderMainDO.setReceiverName("张三");
        orderMainDO.setReceiverPhone("123");
        orderMainDO.setReceiverAddress("铁道");
        orderMainDO.setOrderTime("2023-03-19 17:31:11");
        orderItemDO.setOrderMainDO(orderMainDO);

        System.out.println(orderMapping.OrderItemDOtoOrderVO(orderItemDO));
    }

    @Test
    void orderItemListDOtoOrderVOList() {
        OrderItemDO orderItemDO = new OrderItemDO();
        orderItemDO.setOrderItemId(1);
        orderItemDO.setWhetherShip("已发货");
        orderItemDO.setItemQuantity(2);
        orderItemDO.setProductNameChinese("哈士奇");
        orderItemDO.setItemSpecification("成年雄性");
        OrderMainDO orderMainDO = new OrderMainDO();
        orderMainDO.setReceiverName("张三");
        orderMainDO.setReceiverPhone("123");
        orderMainDO.setReceiverAddress("铁道");
        orderMainDO.setOrderTime("2023-03-19 17:31:11");
        orderItemDO.setOrderMainDO(orderMainDO);

        OrderItemDO orderItemDO2 = new OrderItemDO();
        orderItemDO2.setOrderItemId(1);
        orderItemDO2.setWhetherShip("已发货");
        orderItemDO2.setItemQuantity(2);
        orderItemDO2.setProductNameChinese("哈士奇");
        orderItemDO2.setItemSpecification("成年雄性");
        OrderMainDO orderMainDO2 = new OrderMainDO();
        orderMainDO2.setReceiverName("张三");
        orderMainDO2.setReceiverPhone("123");
        orderMainDO2.setReceiverAddress("铁道");
        orderMainDO2.setOrderTime("2023-03-19 17:31:11");
        orderItemDO2.setOrderMainDO(orderMainDO2);

        List<OrderItemDO> orderItemDOList = new ArrayList<>();
        orderItemDOList.add(orderItemDO);
        orderItemDOList.add(orderItemDO2);
        System.out.println(orderMapping.OrderItemListDOtoOrderVOList(orderItemDOList));
    }
}