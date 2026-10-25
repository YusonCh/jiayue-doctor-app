package com.example.system.controller;

import com.example.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    public String Login(Map<String, String> map) {
        String username = map.get("user_name");
        String password = map.get("user_password");
        return userService.login(username,password);
    }

}
