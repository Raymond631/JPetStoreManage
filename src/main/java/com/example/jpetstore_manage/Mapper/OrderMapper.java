package com.example.jpetstore_manage.Mapper;

import com.example.jpetstore_manage.POJO.DataObject.OrderMainDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author Raymond Li
 * @create 2023-03-20 16:38
 * @description
 */
@Mapper
public interface OrderMapper {
    /**
     * 根据supplier_id查询订单及其子项
     */
    public List<OrderMainDO> selectOrderBySupplier(int supplier);

    /**
     * 根据order_id和supplier_id选择行，修改该行的status字段
     */
    public int updateOrderStatus(int orderId, int status, int supplier);

    public int updateOrderReceiver(OrderMainDO orderMainDO);
}
