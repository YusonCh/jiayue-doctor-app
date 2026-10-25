package com.qys.im.controller;

import com.qys.im.pojo.Health;
import com.qys.im.pojo.Patient;
import com.qys.im.service.HealthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.text.SimpleDateFormat;

import java.util.*;

@RestController
@RequestMapping("/Health")
public class HealthController {

    @Autowired
    private HealthService healthService;


    @GetMapping("/SelectPatientByUserName")
    public List<String> SelectPatientByUserName(@RequestParam Map<String,Object> map){
        String username = (String) map.get("username");

        List<String> patientList =healthService.SelectPatientByUserName(username);

        return patientList;
    }

    @GetMapping("/SelectHealthByUserName")
    public List<Health> SelectHealthByUserName(@RequestParam  Map<String,String> map){
        String username = map.get("username");
        String patient = map.get("patient");
        List<Health> healthList = healthService.SelectHealthByUsername(username,patient);
    //    System.out.println(username);
        return healthList;
    }

    @PostMapping("InsertHealth")
    public String InsertHealth(@RequestBody Map<String, Object> map){
         String username = (String) map.get("username");
         String patientname = (String)map.get("patient");
         int height0 = (int) map.get("height");
         double height = (double) height0;
         int weight0 =(int)map.get("weight");
         double weight = (double) weight0;
         String kpa = (String) map.get("kpa");
         String heartbeat = (String) map.get("heart");
         String sports = (String) map.get("sport");
         String sleep = (String) map.get("sleep");
         String str= (String) map.get("time");
         SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
         Date time = null;
          try {
             time = sdf.parse(str);
          } catch (Exception e) {
               e.printStackTrace();
          }

         healthService.InsertHealth(username,patientname,height,weight,kpa,heartbeat,sports,sleep,time);
         return "插入成功";
    }

    @GetMapping("/GetPatient")
    public List<Map> SelectPatient(@RequestParam("username") String username,@RequestParam("name") String name){
        return healthService.SelectPatient(username, name);
    }

    @PostMapping("/InsertPatient")
    public int  InsertPatient(@RequestBody Map<String,String> map){
        String user_name = (String)map.get("user_name");
        String name = (String) map.get("name");
        String sex = (String) map.get("sex");
        String height0 = (String) map.get("height");
        Double height = Double.valueOf(height0);
        String weight0 = (String) map.get("weight");
        Double weight = Double.valueOf(weight0);
        String allergy = (String) map.get("allergy");
        String illness = (String) map.get("diet");
        String diet = (String) map.get("diet");
        String medicine = (String) map.get("medicine");
        String age = (String) map.get("age");
        String relationship = (String) map.get("relationship");
        return healthService.InsertPatient(user_name, name, sex, height, weight, allergy, illness, diet, medicine, age, relationship);
    }

    @PostMapping("/UpdatePatient")
    public int UpdatePatient(@RequestBody Map<String,String> map){
        String username = map.get("username");
        String name = map.get("name");
        String sex = map.get("sex");
        String weight = map.get("weight");
        String height = map.get("height");
        String allergy = map.get("allergy");
        String medicine = map.get("medicine");
        String diet = map.get("diet");
        String illness = map.get("illness");
        return healthService.UpdatePatient(username, name, sex, weight, height, allergy, medicine, diet, illness);
    }


}
