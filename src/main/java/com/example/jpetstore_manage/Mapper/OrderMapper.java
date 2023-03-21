package com.example.jpetstore_manage.Mapper;

import com.example.jpetstore_manage.POJO.DataObject.OrderItemDO;
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
     * order_item x order_main联表查询(一对一，用association标签)
     * 根据supplier查order_item,再由order_item中的order_id对order_main表进行联表查询
     */
    public List<OrderItemDO> selectOrderItemBySupplier(String supplier);

    /**
     * 此方法供 上面的selectOrderItemBySupplier方法 进行联表查询使用
     * 根据orderId查询该订单的receiver_name、receiver_phone、receiver_address、order_time四个信息
     */
    public OrderMainDO selectOrderMainByOrderId(Long orderId);

    /**
     * 根据order_item_id和选择行，修改该行的whether_ship字段
     */
    public int updateOrderStatus(int orderItemId, String whetherShip);
}
