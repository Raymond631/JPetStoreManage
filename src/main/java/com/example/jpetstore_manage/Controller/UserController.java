package com.example.jpetstore_manage.Controller;

import com.example.jpetstore_manage.POJO.DataObject.UserMainDO;
import com.example.jpetstore_manage.POJO.MapStruct.UserMapping;
import com.example.jpetstore_manage.POJO.ViewObject.Message;
import com.example.jpetstore_manage.POJO.ViewObject.UserVO;
import com.example.jpetstore_manage.Service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

/**
 * @author Raymond Li
 * @create 2023-03-20 16:31
 * @description 用户数据接口，前端通过AJAX请求数据，页面跳转请调用AllPageController中的接口
 * 注册
 * 登录
 * 退出登录
 * 修改密码
 * 修改个人信息（数据库待设计）
 * 扫码登录（拓展功能）
 */
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {
    /**
     * 业务层接口
     */
    @Autowired
    private UserService userService;

    /**
     * 对象转换器
     */
    @Autowired
    private UserMapping userMapping;


    /**
     * 检查验证码
     * 判断两次输入的密码是否相同
     * 对象转换,调用service
     * 将转换后的UserMainDO配置到session
     *
     * @return 返回一个Message对象
     */
    @PostMapping("/register")
    public Message register(@RequestBody UserVO userVO, @SessionAttribute("checkCode") String checkCode, HttpSession session) {
        if (userVO.getVCode().equalsIgnoreCase(checkCode)) {
            if (userVO.getPassword().equals(userVO.getRePassword())) {
                // 对象转换
                UserMainDO userMainDO = userMapping.toUserMainDO(userVO);
                // MD5加密
                userMainDO.setPassword(DigestUtils.md5DigestAsHex(userMainDO.getPassword().getBytes()));
                // 注册
                Message message = userService.register(userMainDO);
                // 如果注册成功，同时也把loginUser配置到session中（不用再登陆了）
                if (message.getCode() == 1) {
                    session.setAttribute("loginUser", userMainDO);
                }
                return message;
            } else {
                return new Message(0, "两次输入的密码不一致");
            }
        } else {
            return new Message(0, "验证码错误");
        }
    }

    /**
     * 检查验证码
     * 对象转换,调用service
     * 将转换后的UserMainDO配置到session
     *
     * @return 返回一个Message对象
     */
    @PostMapping("/login")
    public Message login(@RequestBody UserVO userVO, @SessionAttribute("checkCode") String checkCode, HttpSession session) {
        if (userVO.getVCode().equalsIgnoreCase(checkCode)) {
            // 对象转换
            UserMainDO userMainDO = userMapping.toUserMainDO(userVO);
            // MD5加密
            userMainDO.setPassword(DigestUtils.md5DigestAsHex(userMainDO.getPassword().getBytes()));
            // 登录
            Message message = userService.login(userMainDO);
            // 如果注册成功，配置到session
            if (message.getCode() == 1) {
                session.setAttribute("loginUser", userMainDO);
            }
            return message;
        } else {
            return new Message(0, "验证码错误");
        }
    }


    /**
     * 将“loginUser”从session中移除
     *
     * @return 返回一个Message对象
     */
    @DeleteMapping("/signOut")
    public Message signOut(HttpSession session) {
        session.setAttribute("loginUser", null);
        return new Message(1, "退出登录成功");
    }

    /**
     * 检查验证码
     * 判断两次输入的密码是否相同
     * 对象转换,调用service
     *
     * @return 返回一个Message对象
     */
    @PutMapping("/changePassword")
    public Message changePassword(@RequestBody UserVO userVO, @SessionAttribute("checkCode") String checkCode) {
        if (userVO.getVCode().equalsIgnoreCase(checkCode)) {
            if (userVO.getPassword().equals(userVO.getRePassword())) {
                // 旧密码
                UserMainDO oldUserMainDO = userMapping.toOldUserMainDO(userVO);
                oldUserMainDO.setPassword(DigestUtils.md5DigestAsHex(oldUserMainDO.getPassword().getBytes()));
                // 新密码
                UserMainDO newUserMainDO = userMapping.toUserMainDO(userVO);
                newUserMainDO.setPassword(DigestUtils.md5DigestAsHex(newUserMainDO.getPassword().getBytes()));
                // 修改密码,返回提示信息
                return userService.changePassword(oldUserMainDO, newUserMainDO);
            } else {
                return new Message(0, "两次输入的密码不一致");
            }
        } else {
            return new Message(0, "验证码错误");
        }
    }
}