package com.qys.im.service;

import com.qys.im.dao.HealthDao;
import com.qys.im.pojo.Health;
import com.qys.im.pojo.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class HealthService {

    @Autowired
    private HealthDao healthDao;

    public List<Health> SelectHealthByUsername(String username,String patient){
   //     System.out.println(username);
        return healthDao.SelectHealthByUserName(username,patient);
    }

    public List<String> SelectPatientByUserName(String username){
        return healthDao.SelectPatientByUserName(username);
    }

    public String InsertHealth(String username, String patient_name, double height, double weight, String kpa, String heartbeat, String sports, String sleep, Date time){
        healthDao.InsertHealth(username,patient_name,height,weight,kpa,heartbeat,sports,sleep,time);
        return "插入成功";
    }

    public List<Map> SelectPatient(String username,String name){
        return healthDao.SelectPatient(username, name);
    }

    public  int UpdatePatient(String username,String name,String sex,String weight,String height,String allergy,String medicine,String diet,String illness){
        return healthDao.UpdatePatient(username, name, sex, weight, height, allergy, medicine, diet, illness);
    }

    public int InsertPatient(String user_name, String name, String sex, double height, double weight, String allergy, String illness, String diet, String medicine, String age, String relationship){
        return healthDao.InsertPatient(user_name, name, sex, height, weight, allergy, illness, diet, medicine, age, relationship);
    }

}
