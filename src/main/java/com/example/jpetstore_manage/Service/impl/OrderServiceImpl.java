package com.example.jpetstore_manage.Service.impl;

import com.example.jpetstore_manage.Mapper.OrderMapper;
import com.example.jpetstore_manage.POJO.DataObject.OrderItemDO;
import com.example.jpetstore_manage.POJO.ViewObject.CommonResponse;
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
    public List<OrderItemDO> getOrderItemList(String supplier) {
        return orderMapper.selectOrderItemBySupplier(supplier);
    }

    @Override
    public CommonResponse ship(int orderItemId, String supplier) {
        CommonResponse commonResponse = new CommonResponse();
        int status = orderMapper.updateOrderStatus(orderItemId, "已发货", supplier);
        if (status == 1) {
            commonResponse.setCode(1);
            commonResponse.setMsg("修改成功");
            return commonResponse;
        } else {
            commonResponse.setCode(0);
            commonResponse.setMsg("修改失败");
            return commonResponse;
        }

    }


}
