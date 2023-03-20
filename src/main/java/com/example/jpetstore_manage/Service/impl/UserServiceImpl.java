package com.example.jpetstore_manage.Service.impl;

import com.example.jpetstore_manage.Mapper.UserMapper;
import com.example.jpetstore_manage.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Raymond Li
 * @create 2023-03-20 16:33
 * @description
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

}
