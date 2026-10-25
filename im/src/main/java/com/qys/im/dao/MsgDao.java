package com.qys.im.dao;


import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface MsgDao {

    int addMsg(String illid,String sendUserName,String receiveUserName,String sendName,String receiveName,String sendText,String updateTime,String createTime,int TextType);

    List<Map<String,Object>> selectMsg(String sendUserName,String receiveUserName,int illid);

    List<Map<String,Object>> SelectMsg();

}
