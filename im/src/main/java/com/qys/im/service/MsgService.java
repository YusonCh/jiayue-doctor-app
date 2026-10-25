package com.qys.im.service;

import com.qys.im.dao.MsgDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class MsgService {

    @Autowired
    private MsgDao msgDao;

    public int addMsg(String illid,String sendUserName,String receiveUserName,String sendName,String receiveName,String sendText,String updateTime,String createTime,int TextType){
        return msgDao.addMsg(illid,sendUserName,receiveUserName,sendName,receiveName,sendText,updateTime,createTime,TextType);
    }

    public List<Map<String,Object>> selectMsg(String sendUserName,String receiveUserName,int illid){
        return msgDao.selectMsg(sendUserName,receiveUserName,illid);
    }

    public List<Map<String,Object>> SelectMsg(){
        return msgDao.SelectMsg();
    }
}
