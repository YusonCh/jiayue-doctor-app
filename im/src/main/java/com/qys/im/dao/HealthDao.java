package com.qys.im.dao;

import com.qys.im.pojo.Health;
import com.qys.im.pojo.Patient;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface HealthDao {

    List<Health> SelectHealthByUserName(String username,String patient);

    List<String> SelectPatientByUserName(String username);

    int InsertHealth(String username, String patient,double height, double weight, String kpa,String heart,String sport,String sleep,Date time);

    List<Map> SelectPatient(String username,String name);

    List<String> selectPatient(String username);
    int UpdatePatient(String username,String name,String sex,String weight,String height,String allergy,String medicine,String diet,String illness);

    int InsertPatient(String user_name, String name, String sex, double height, double weight, String allergy, String illness, String diet, String medicine, String age, String relationship);
}
