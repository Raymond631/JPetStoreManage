package com.example.jpetstore_manage.POJO.MapStruct;

import com.example.jpetstore_manage.POJO.DataObject.OrderItemDO;
import com.example.jpetstore_manage.POJO.ViewObject.OrderVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * @author Raymond Li
 * @create 2023-03-21 11:31
 * @description
 */
@Mapper(componentModel = "spring")
public interface OrderMapping {
    @Mapping(target = "id", source = "orderItemId")
    @Mapping(target = "name", source = "orderMainDO.receiverName")
    @Mapping(target = "phone", source = "orderMainDO.receiverPhone")
    @Mapping(target = "position", source = "orderMainDO.receiverAddress")
    @Mapping(target = "date", source = "orderMainDO.orderTime")
    @Mapping(target = "status", source = "whetherShip")
    @Mapping(target = "amount", source = "itemQuantity")
    @Mapping(target = "category", source = "productNameChinese")
    @Mapping(target = "specification", source = "itemSpecification")
    public OrderVO OrderItemDOtoOrderVO(OrderItemDO orderItemDO);

    public List<OrderVO> OrderItemListDOtoOrderVOList(List<OrderItemDO> orderItemDOList);
}
