package com.qys.im.controller;

import com.qys.im.annotation.LoginUser;
import com.qys.im.pojo.User;
import com.qys.im.service.UserPatientService;
import com.qys.im.service.UserService;
import com.qys.im.utils.ResponseUtils;
import org.apache.logging.log4j.util.PerformanceSensitive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/register_patient")
public class UserPatientController {

    @Autowired
    private UserPatientService userPatientService;

    @Autowired
    private UserService userService;

    @PostMapping ("/signupByUserName")
    public int InsertUser(@RequestBody Map<String,Object> map){
        String user_name = (String) map.get("user_name");
        String password = (String) map.get("password");
        int usertype = (int) map.get("usertype");
        String id = (String)map.get("id");
        String connect = (String) map.get("connect");

        int a = userService.InsertUser(user_name,password,usertype,id);
        return this.getUserByUserName(user_name,password,connect);
    }

    @PostMapping ("/signupByDoctorName")
    public int InsertDoctor(@RequestBody Map<String,Object> map){
        String user_name = (String) map.get("user_name");
        String password = (String) map.get("password");
        int usertype = (int) map.get("usertype");
        String id = (String)map.get("id");
        return userService.InsertUser(user_name,password,usertype,id);

    }

    public int getUserByUserName(String username,String password,String connect){
        return userPatientService.signupByUserName(username,password,connect);
    }

    @GetMapping("/SelectPatient")
    public List<Map<String,String>> SelectUserPatient(String username){
        return userPatientService.SelectUserPatient(username);
    }

    @PostMapping("/updateConnect")
    public int updateConnect(@RequestBody Map<String,String> map){
        String username = (String)map.get("username");
        String connect = (String) map.get("connect");

        System.out.println(username);

        return userPatientService.updateConnect(username,connect);
    }

    @PostMapping("/updateName")
    public int updateName(@RequestBody Map<String,String> map){
        String username = (String)map.get("username");
        String name = (String) map.get("name");

        //System.out.println(username);

        return userPatientService.updateName(username,name);
    }

    @PostMapping("/updateImage")
    public int updateImage(@RequestBody Map<String,String> map){
        String username = (String)map.get("username");
        String image = (String) map.get("image");

        //System.out.println(username);

        return userPatientService.updateImage(username,image);
    }
}
