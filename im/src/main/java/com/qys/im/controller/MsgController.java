package com.qys.im.controller;

import com.qys.im.service.MsgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/msg")
public class MsgController {

    @Autowired
    private MsgService msgService;

    @PostMapping("/addMsg")
    public int addMsg(@RequestBody Map<String,Object> map){
        String illid = (String)map.get("illid");
        String sendUserName = (String)map.get("sendUserName");
        String receiveUserName= (String)map.get("receiveUserName");
        String sendName= (String)map.get("sendName");
        String receiveName= (String)map.get("receiveName");
        String sendText= (String)map.get("sendText");
        String updateTime= (String) map.get("updateTime");
        String createTime= (String) map.get("createTime");
        int TextType= (int)map.get("TextType");
        return msgService.addMsg(illid,sendUserName,receiveUserName,sendName,receiveName,sendText,updateTime,createTime,TextType);
    }

    @GetMapping("/selectMsg")
    public List<Map<String,Object>> selectMsg( String sendUserName, String receiveUserName,String illid){
        int ill_id = Integer.valueOf(illid);
        return msgService.selectMsg(sendUserName,receiveUserName,ill_id);
    }

    @GetMapping("/SelectMsg")
    public List<Map<String,Object>> SelectMsg(){
        return msgService.SelectMsg();
    }
}
