package com.example.jpetstore_manage.Service.impl;

import com.example.jpetstore_manage.Common.CommonResponse;
import com.example.jpetstore_manage.Mapper.OrderMapper;
import com.example.jpetstore_manage.POJO.DataObject.OrderMainDO;
import com.example.jpetstore_manage.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Raymond Li
 * @create 2023-03-20 16:34
 * @description
 */
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderMapper orderMapper;

    /**
     * 查询订单列表
     */
    @Override
    public List<OrderMainDO> getOrderList(int supplier) {
        return orderMapper.selectOrderBySupplier(supplier);
    }

    /**
     * 发货
     */
    @Override
    public CommonResponse ship(int[] orderIdList, int supplier) {
        boolean isSuccessful = true;

        for (int i : orderIdList) {
            int num = orderMapper.updateOrderStatus(i, 2, supplier);
            if (num != 1) {
                isSuccessful = false;
            }
        }

        if (isSuccessful) {
            return CommonResponse.success("发货成功");
        } else {
            return CommonResponse.error("发货失败");
        }
    }

    @Override
    public CommonResponse changeReceiver(OrderMainDO orderMainDO, int orderId, int supplier) {
        orderMainDO.setOrderId(orderId);
        orderMainDO.setSupplierId(supplier);
        int num = orderMapper.updateOrderReceiver(orderMainDO);
        if (num == 1) {
            return CommonResponse.success("修改成功");
        } else {
            return CommonResponse.error("修改失败");
        }
    }

    @Override
    public CommonResponse deleteOrder(int[] orderIdList, int supplier) {
        boolean isSuccessful = true;

        for (int i : orderIdList) {
            int num = orderMapper.updateOrderStatus(i, 5, supplier);
            if (num != 1) {
                isSuccessful = false;
            }
        }

        if (isSuccessful) {
            return CommonResponse.success("删除成功");
        } else {
            return CommonResponse.error("删除失败");
        }
    }

}
