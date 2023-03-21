package com.example.jpetstore_manage.Service;

import com.example.jpetstore_manage.POJO.DataObject.OrderItemDO;

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
}
