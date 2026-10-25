package com.qys.im.service;

import com.qys.im.Topsis.topsis.TopsisIncompleteAlternativeDataException;
import com.qys.im.dao.RateDao;
import com.qys.im.dao.TopsisDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class RateService {

    @Autowired
    private RateDao rateDao;

    @Autowired
    private TopsisDao topsisDao;

    @Autowired
    private TopsisService topsisService;

    public int DoctorRate(String doctor_user,String patient_user,String name,int answer_quality0,int answer_speed0,int answer_attitude0,int answer_quantity0,int answer_influence0,int answer_satisfaction0){

        List<Map<String, Integer>> temp = rateDao.SelectRate(doctor_user);
        int temp_length =  temp.size();
        int temp_now = temp_length+1;
        int answer_quality=0;
        int answer_speed=0;
        int answer_attitude=0;
        int answer_quantity=0;
        int answer_influence=0;
        int answer_satisfaction=0;

        for(int i=0;i<temp_length;i++){
            Map<String,Integer> tp = temp.get(i);
            answer_quality += tp.get("answer_quality");
            answer_speed += tp.get("answer_speed");
            answer_attitude+= tp.get("answer_attitude");
            answer_quantity+= tp.get("answer_quantity");
            answer_influence+= tp.get("answer_influence");
            answer_satisfaction+= tp.get("answer_satisfaction");
        }

        answer_quality += answer_quality0;
        answer_speed += answer_speed0;
        answer_attitude+= answer_attitude0;
        answer_quantity+= answer_quantity0;
        answer_influence+= answer_influence0;
        answer_satisfaction+= answer_satisfaction0;

        answer_quality /= temp_now;
        answer_speed /= temp_now;
        answer_attitude/= temp_now;
        answer_quantity/= temp_now;
        answer_influence/= temp_now;
        answer_satisfaction/= temp_now;

        topsisDao.UpdateEvaluation(doctor_user,answer_quality,answer_speed,answer_attitude,answer_quantity,answer_influence,answer_satisfaction);

        try {
            topsisService.testMobileMCDM();
        } catch (TopsisIncompleteAlternativeDataException e) {
            System.err.println(e.getMessage());
        }

//        System.out.println(answer_quality);
//        System.out.println(answer_speed);
//        System.out.println(answer_attitude);
//        System.out.println(answer_quantity);
//        System.out.println(answer_influence);
//        System.out.println(answer_satisfaction);

        return rateDao.DoctorRate(doctor_user,patient_user,name, answer_quality0, answer_speed0, answer_attitude0, answer_quantity0, answer_influence0, answer_satisfaction0);
    }
}
