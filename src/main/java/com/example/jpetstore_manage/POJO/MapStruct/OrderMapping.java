package com.example.jpetstore_manage.POJO.MapStruct;

import com.example.jpetstore_manage.POJO.DataObject.OrderItemDO;
import com.example.jpetstore_manage.POJO.DataObject.OrderMainDO;
import com.example.jpetstore_manage.POJO.ViewObject.OrderVO;
import com.example.jpetstore_manage.POJO.ViewObject.ReceiverVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

/**
 * @author Raymond Li
 * @create 2023-03-21 11:31
 * @description
 */
@Mapper(componentModel = "spring")
public interface OrderMapping {
    @Named("mergeItem")
    static String mergeItem(List<OrderItemDO> orderItemDOList) {
        StringBuilder items = new StringBuilder();
        for (OrderItemDO orderItemDO : orderItemDOList) {
            items.append(orderItemDO.getProductNameChinese())
                    .append("/")
                    .append(orderItemDO.getItemSpecification())
                    .append("/")
                    .append(orderItemDO.getItemQuantity())
                    .append("个;\n");
        }
        return items.toString();
    }

    @Mapping(target = "items", source = "orderItemDOList", qualifiedByName = "mergeItem")
    public OrderVO toOrderVO(OrderMainDO orderMainDO);

    public List<OrderVO> toOrderVOList(List<OrderMainDO> orderMainDOList);

    public OrderMainDO toOrderMainDO(ReceiverVO receiverVO);
}
