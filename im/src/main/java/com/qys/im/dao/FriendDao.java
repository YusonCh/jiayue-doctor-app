package com.qys.im.dao;

import com.qys.im.pojo.Friend;
import com.qys.im.pojo.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface FriendDao {
    List<User> list(Long id);
    int add(Friend friend);


}
