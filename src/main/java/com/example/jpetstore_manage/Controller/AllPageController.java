package com.example.jpetstore_manage.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Raymond Li
 * @create 2023-03-20 18:50
 * @description 请求页面控制器，所有的页面请求都由这个控制器跳转
 */
@Controller
public class AllPageController {
    /**
     * 更路径映射，访问http://localhost:8888/jpetstore时，重定向到首页
     */
    @GetMapping("/")
    public String showIndex() {
        return "redirect:/Login.html";
    }

    /**
     * 在未登录状态下，如果用户请求了需要登录的接口，即会被拦截器拦截，自动跳到NotLogin.html
     */
    @GetMapping("/NotLogin.html")
    public String showNotLogin() {
        return "/Error/NotLogin";
    }

    /**
     * 登录页面跳转接口
     */
    @GetMapping("/Login.html")
    public String showLogin() {
        return "User/Login";
    }

    /**
     * 注册页面跳转接口
     */
    @GetMapping("/Register.html")
    public String showRegister() {
        return "User/Register";
    }

    /**
     * 个人中心页面跳转接口
     */
    @GetMapping("/SelfCenter.html")
    public String showSelfCenter() {
        return "User/SelfCenter";
    }

    /**
     * 订单管理页面
     */
    @GetMapping("/OrderManage.html")
    public String showOrderManage() {
        return "/Order/OrderManage";
    }

    /**
     * 商品管理页面
     */
    @GetMapping("/ProductManage.html")
    public String showProductManage() {
        return "/Pet/ProductManage";
    }
}
