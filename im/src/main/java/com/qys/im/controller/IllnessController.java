package com.qys.im.controller;

import com.qys.im.pojo.Illness_Record;
import com.qys.im.service.IllnessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/illness")

public class IllnessController {
    @Autowired
    private IllnessService illnessService;


    @GetMapping("/SelectIllness")
    public  List<Illness_Record> SelectIllnessReport(@RequestParam Map<String,String> map){
        String username = (String) map.get("username");
        System.out.println(username);
        return illnessService.SelectIllnessReport(username);
    }

    @GetMapping("/SelectIllnessByDoctor")
    public  List<Illness_Record> SelectIllnessReportByDoctor(@RequestParam Map<String,String> map){
        String username = (String) map.get("username");
        System.out.println(username);
        return illnessService.SelectIllnessReportByDoctor(username);
    }

    @GetMapping("/Select")
    public  List<Illness_Record> SelectIllness(@RequestParam Map<String,String> map){
        String name = (String) map.get("name");
        String doctor = (String) map.get("doctor");
        System.out.println(name);
        return illnessService.SelectIllness(name,doctor);
    }

    @PostMapping("/InsertIllness")
    public int InsertIllness(@RequestBody Map<String,Object> map){

        String name = (String)map.get("name");
        String username = (String)map.get("username");
        String doctor = (String)map.get("doctor");
        String illness = (String)map.get("illness");
        System.out.println(illness);
        String str= (String) map.get("date");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = sdf.parse(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String end = "0";


        return illnessService.InsertIllness(username,name,doctor,date,illness,end);
    }

    @PostMapping("/UpdateIllness")
    public int UpdateIllness(@RequestBody Map<String,Object> map){
        String reason = (String)map.get("reason");
        String medicine = (String)map.get("medicine");
        String diet = (String)map.get("diet");
        String cure = (String) map.get("cure");
        int id = 14;
        return illnessService.UpdateIllness(reason,medicine,diet,cure, id);
    }
    @GetMapping("/SelectNumber")
    public int SelectNumber(){
        return illnessService.SelectNumber();
    }

}


