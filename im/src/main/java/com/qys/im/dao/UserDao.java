package com.qys.im.dao;

import com.qys.im.pojo.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface UserDao {

    User getUserByUserName(String username);//照用户名查询

   // User insertUserByUsername(String username,String password);

    int getUserTypeByUserName(String username);//照用户名查询

    List<String> SelectDoctorName (int user_type);

    int InsertUser(String username,String password,int user_type,String id);

    String SelectId(String username);

    int UpdatePassword(String username,String password);

    List<Map<String,Object>> SelectUser();

    int UpdateUser(String username,String password,int usertype);

    int DeleteUser(String username,int usertype);
}
