package com.example.system.service;

import com.example.system.dao.UserDao;
import com.example.system.pojo.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UserService{
    @Autowired
    UserDao userDao;

    public String login(String user_name,String user_password) {

        if (user_name == null) {
            return "账户不存在";
        } else {
            if (user_password == userDao.getBypassword(user_name)) {
                return "登录成功";
            } else {
                return "登录失败";
            }
        }

    }
}

