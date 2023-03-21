package com.example.jpetstore_manage.POJO.ViewObject;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Raymond Li
 * @create 2023-03-21 10:56
 * @description
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderVO {
    private int id;// orderItemId
    private String name;// receiverName
    private String phone;// receiverPhone
    private String position;// receiverAddress
    private String date;// orderTime
    private String status;// whetherShip
    private int amount;// itemQuantity
    private String category;// productNameChinese
    private String specification;// itemSpecification
}
