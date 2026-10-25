package com.qys.im.service;

import com.qys.im.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserService {
    @Autowired
    private UserDao userDao;

    public Object getUserByUserName(String username) {
       return userDao.getUserByUserName(username);
    }

    public int getUserTypeByUserName(String username){
        return userDao.getUserTypeByUserName(username);
    }

    public int InsertUser(String username,String password,int usertype,String id){
        return userDao.InsertUser(username,password,usertype,id);
    }

    public String SelectId(String username){
        return userDao.SelectId(username);
    }

    public int UpdatePassword(String username,String password){
        return userDao.UpdatePassword(username,password);
    }

    public List<Map<String,Object>> SelectUser(){
        return userDao.SelectUser();
    }

    public int UpdateUser(String username,String password,int user_type){
        return userDao.UpdateUser(username,password,user_type);
    }

    public int DeleteUser(String username,int usertype){
        return userDao.DeleteUser(username,usertype);
    }


}
