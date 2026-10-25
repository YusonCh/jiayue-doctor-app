package com.qys.im.dao;

import com.qys.im.pojo.User_Patient;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface UserPatientDao {

    int signupByUserName(String username,String password,String connect);

    List<Map<String,String >> SelectUserPatient(String username);

    int updateConnect(String username,String connect);

    int updateName(String username,String name);

    int updateImage(String username,String image);

}
