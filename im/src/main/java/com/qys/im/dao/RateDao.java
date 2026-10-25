package com.qys.im.dao;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface RateDao {

    int DoctorRate(String doctor_user,String patient_user,String name,int answer_quality,int answer_speed,int answer_attitude,int answer_quantity,int answer_influence,int answer_satisfaction);

    List<Map<String, Integer>> SelectRate(String username);
}
