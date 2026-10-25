package com.qys.im.controller;

import com.qys.im.dao.TopsisDao;
import com.qys.im.service.RateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/rate")
public class RateController {
    @Autowired
    private RateService rateService;



    @PostMapping("/doctorRate")
    public int DoctorRate(@RequestBody Map<String,Object> map){
        String doctor_user = (String) map.get("doctor_user");
        String patient_user = (String) map.get("patient_user");
        String name = (String) map.get("name");
        int answer_quality = (int) map.get("answer_quality");
        int answer_speed = (int) map.get("answer_speed");
        int answer_attitude = (int)map.get("answer_attitude");
        int answer_quantity =(int) map.get("answer_quantity");
        int answer_influence = (int) map.get("answer_influence");
        int answer_satisfaction = (int)map.get("answer_satisfaction");


        return rateService.DoctorRate(doctor_user,patient_user, name,answer_quality, answer_speed, answer_attitude, answer_quantity, answer_influence, answer_satisfaction);
    }
}
