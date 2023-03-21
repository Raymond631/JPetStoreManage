package com.example.jpetstore_manage.Service;

import com.example.jpetstore_manage.POJO.DataObject.OrderItemDO;
import com.example.jpetstore_manage.POJO.ViewObject.Message;

import java.util.List;

/**
 * @author Raymond Li
 * @create 2023-03-20 16:32
 * @description
 */
public interface OrderService {
    /**
     * 查询订单列表
     */
    List<OrderItemDO> getOrderItemList(String supplier);

    /**
     * 发货，修改订单状态为“已发货”
     */
    Message ship(int orderItemId, String supplier);
}
