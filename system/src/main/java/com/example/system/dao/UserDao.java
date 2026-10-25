package com.example.system.dao;

import com.example.system.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;


@Mapper
@Repository
public interface UserDao {
    //    通过账号判断用户是否已经注册
    int getUserNumberByAccount(@Param("account") String account);

    //    通过账户密码获取用户信息，验证用户是否存在
    String getBypassword(@Param("account") String account);

}

