package com.example.jpetstore_manage.Controller;

import com.example.jpetstore_manage.POJO.DataObject.OrderItemDO;
import com.example.jpetstore_manage.POJO.DataObject.UserAuthDO;
import com.example.jpetstore_manage.POJO.MapStruct.OrderMapping;
import com.example.jpetstore_manage.POJO.ViewObject.CommonResponse;
import com.example.jpetstore_manage.POJO.ViewObject.OrderVO;
import com.example.jpetstore_manage.Service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Raymond Li
 * @create 2023-03-20 16:31
 * @description 订单数据接口，前端通过AJAX请求数据，页面跳转请调用AllPageController中的接口
 * 获取订单数据
 * 发货
 * 删除订单（能删吗？）
 * 修改订单（能改吗？）
 */
@Slf4j
@RestController
@RequestMapping("/order")
public class OrderController {
    /**
     * 业务层接口
     */
    @Autowired
    private OrderService orderService;

    /**
     * 对象转换器
     */
    @Autowired
    private OrderMapping orderMapping;

    /**
     * 调用service层，查找供应商为“当前用户”的订单(userMainDO代表当前用户)
     * 对象转换，返回前端
     */
    @GetMapping("/orders")
    public List<OrderVO> getOrderList(@SessionAttribute("loginUser") UserAuthDO userAuthDO) {
        List<OrderItemDO> orderItemDOList = orderService.getOrderItemList(userAuthDO.getUserId());
        return orderMapping.toOrderVOList(orderItemDOList);
    }

    /**
     * 发货
     */
    @PutMapping("/orders/{orderItemId}")
    public CommonResponse ship(@PathVariable("orderItemId") int orderItemId, @SessionAttribute("loginUser") UserAuthDO userAuthDO) {
        return orderService.ship(orderItemId, userAuthDO.getUserId());
    }
}
