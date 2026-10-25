package com.qys.im.controller;

import com.qys.im.annotation.LoginUser;
import com.qys.im.pojo.User;
import com.qys.im.service.UserService;
import com.qys.im.utils.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public Object User(@LoginUser User user){
        return ResponseUtils.ok(user);
    }

    @GetMapping("/getUserByUserName")
    public Object getUserByUserName(String username){
        return ResponseUtils.ok(userService.getUserByUserName(username));
    }

    @GetMapping("/getUserTypeByUserName")
    public int getUserTypeByUserName(@RequestParam("username") String username){
        System.out.println(username);
        int a = userService.getUserTypeByUserName(username);
        System.out.println(a);
        return userService.getUserTypeByUserName(username);
    }

    @GetMapping("/SelectId")
    public String SelectId(@RequestParam("username") String username){
        return userService.SelectId(username);
    }

    @PostMapping("/UpdatePassword")
    public int UpdatePassword(@RequestBody Map<String,String> map){
        String username = map.get("username");
        String password = map.get("password");

        return userService.UpdatePassword(username,password);
    }

    @GetMapping("/SelectUser")
    public List<Map<String,Object>> SelectUser(){
        return userService.SelectUser();
    }

    @PostMapping("/UpdateUser")
    public int UpdateUser(@RequestBody Map<String,Object> map) {
        String username = (String)map.get("username");
        String password = (String) map.get("password");
        String usertype = (String) map.get("user_type");
        int user_type = Integer.valueOf(usertype);
        return userService.UpdateUser(username,password,user_type);
    }

    @PostMapping("/DeleteUser")
    public int DeleteUser(@RequestBody Map<String,Object> map) {
        String username = (String)map.get("username");
        int usertype = (int) map.get("user_type");

        return userService.DeleteUser(username,usertype);
    }
}
