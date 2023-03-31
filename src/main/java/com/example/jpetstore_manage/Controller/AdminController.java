package com.example.jpetstore_manage.Controller;

import com.example.jpetstore_manage.Common.CommonResponse;
import com.example.jpetstore_manage.Common.JwtUtil;
import com.example.jpetstore_manage.POJO.DataObject.UserInfoDO;
import com.example.jpetstore_manage.Service.AdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Raymond Li
 * @create 2023-03-31 23:00
 * @description
 */
@Slf4j
@RestController
public class AdminController {
    @Autowired
    private AdminService adminService;

    @GetMapping("/users/all")
    public CommonResponse getAllUser(@CookieValue("token") String token) {
        int userId = (int) JwtUtil.resolveToken(token).get("userId");
        // 超级管理员
        if (userId == 1) {
            List<UserInfoDO> userInfoDOList = adminService.getAllUser();
            return CommonResponse.success(userInfoDOList);
        } else {
            return CommonResponse.unauthorized("您没有权限访问此页面");
        }
    }

    @DeleteMapping("/users/{userId}")
    public CommonResponse removeUser(@PathVariable("userId") int userId, @CookieValue("token") String token) {
        int admin = (int) JwtUtil.resolveToken(token).get("userId");
        // 超级管理员
        if (admin == 1) {
            adminService.removeUser(userId);
            return CommonResponse.success("删除成功");
        } else {
            return CommonResponse.unauthorized("您没有权限访问此页面");
        }
    }

    @PutMapping("/users")
    public CommonResponse updateUserInfo(@RequestBody UserInfoDO userInfoDO, @CookieValue("token") String token) {
        int admin = (int) JwtUtil.resolveToken(token).get("userId");
        // 超级管理员
        if (admin == 1) {
            adminService.updateUserInfo(userInfoDO);
            return CommonResponse.success("修改成功");
        } else {
            return CommonResponse.unauthorized("您没有权限访问此页面");
        }
    }
}
