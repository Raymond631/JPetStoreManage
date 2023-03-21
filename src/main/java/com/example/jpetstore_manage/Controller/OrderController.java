package com.example.jpetstore_manage.Controller;

import com.example.jpetstore_manage.POJO.DataObject.UserMainDO;
import com.example.jpetstore_manage.POJO.MapStruct.OrderMapping;
import com.example.jpetstore_manage.Service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

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
public class OrderController {
    @Autowired
    private OrderService orderService;

    /**
     * 对象转换器
     */
    @Autowired
    private OrderMapping orderMapping;

    /**
     * 调用service层，查找供应商为“当前用户”的订单
     * 对象转换，返回前端
     */
    @GetMapping("/list")
    public Object getOrderList(@SessionAttribute("loginUser") UserMainDO userMainDO) {
        return null;
    }
}
