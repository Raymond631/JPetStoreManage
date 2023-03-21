package com.example.jpetstore_manage.Mapper;

import com.example.jpetstore_manage.POJO.DataObject.OrderItemDO;
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
     * 根据supplier查order_item,再由order_item中的order_id进行联表查询(用association标签)
     */
    public List<OrderItemDO> selectOrderItemBySupplier(String supplier);
}
