package com.qys.im.service;

import com.qys.im.dao.UserDao;
import com.qys.im.dao.UserPatientDao;
import com.qys.im.pojo.User_Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserPatientService {

    @Autowired
    private UserPatientDao userPatientDao;

    public int  signupByUserName(String username,String password,String connect) {

      return  userPatientDao.signupByUserName(username,password,connect);
    }

    public List<Map<String,String >> SelectUserPatient(String username){
        return userPatientDao.SelectUserPatient(username);
    }

    public int updateConnect(String username,String connect){
        return userPatientDao.updateConnect(username,connect);
    }

    public int updateName(String username,String name){
        return userPatientDao.updateName(username,name);
    }

    public int updateImage(String image,String name){
        return userPatientDao.updateImage(image, name);
    }

}
