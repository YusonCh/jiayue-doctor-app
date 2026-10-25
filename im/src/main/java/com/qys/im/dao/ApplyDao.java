package com.qys.im.dao;

import com.qys.im.pojo.Apply;
import com.qys.im.pojo.User;

import java.util.List;

public interface ApplyDao {
    int add(Apply apply);
    List<User> getFriends(Long id);
}
