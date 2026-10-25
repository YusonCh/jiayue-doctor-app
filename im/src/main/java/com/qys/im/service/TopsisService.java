package com.qys.im.service;

import com.qys.im.Topsis.topsis.Alternative;
import com.qys.im.Topsis.topsis.Criteria;
import com.qys.im.Topsis.topsis.Topsis;
import com.qys.im.Topsis.topsis.TopsisIncompleteAlternativeDataException;
import com.qys.im.dao.TopsisDao;
import com.qys.im.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

import com.qys.im.Topsis.*;
import org.springframework.web.bind.annotation.GetMapping;

import static org.junit.Assert.assertEquals;

@Service
public class TopsisService {
    @Autowired
    private TopsisDao topsisDao;

 //   public void InsertEvaluation(String user_name,double answer_quality,double answer_speed,double answer_attitude,double answer_quantity,double answer_influence,double answer_satisfaction){

    public void InsertEvaluation(){

            for(int i=1;i<=10;i++){
            Random r=new Random();
            int answer_quality=r.nextInt(101);
            int answer_speed=r.nextInt(101);
            int answer_attitude=r.nextInt(101);
            int answer_quantity=r.nextInt(101);
            int answer_influence=r.nextInt(101);
            int answer_satisfaction=r.nextInt(101);

            String a =String.valueOf(i);
            String user_name = "user1"+a;

            topsisDao.InsertEvaluation(user_name, answer_quality, answer_speed, answer_attitude, answer_quantity, answer_influence, answer_satisfaction);
        }

    //    topsisDao.InsertEvaluation(user_name, answer_quality, answer_speed, answer_attitude, answer_quantity, answer_influence, answer_satisfaction);
    }

    public List<Map<String,Integer>> SelectEvaluation(String user_name){
        return topsisDao.SelectEvaluation(user_name);
    }

    @Autowired
    private UserDao userDao;
    public List<String> SelectDoctorName(int user_type){
        return userDao.SelectDoctorName(user_type);
    }

    public void UpdateResult(String username,double result){
        topsisDao.UpdateResult(username,result);
    }

    public void testMobileMCDM() throws TopsisIncompleteAlternativeDataException {

        Criteria criteriaQuality = new Criteria("answer_quality", 0.15);
        //响应质量
        Criteria criteriaSpeed = new Criteria("answer_speed", 0.15,true);
        //响应速度，越快越好，所以negative：true将指标正向化
        Criteria criteriaAttitude = new Criteria("answer_attitude", 0.2);
        //回答态度
        Criteria criteriaQuantity = new Criteria("answer_quantity", 0.25);
        //回答数量
        Criteria criteriaInfluence = new Criteria("answer_influence", 0.1);
        //回答效果
        Criteria criteriaSatisfaction = new Criteria("answer_satisfaction", 0.15);
        //回答满意度

        Topsis topsis = new Topsis();

        List<String> listName = this.SelectDoctorName(2);
        //读取usertype为2的所有username

        int length = listName.size();

        for(int i=0;i<length;i++){
            String username = String.valueOf(listName.get(i));

            List<Map<String, Integer>> list = this.SelectEvaluation(username);

            for (Map<String, Integer> map : list) {
                int quality = map.get("answer_quality");
                int speed = map.get("answer_speed");
                int attitude = map.get("answer_attitude");
                int quantity = map.get("answer_quantity");
                int influence = map.get("answer_influence");
                int satisfaction = map.get("answer_satisfaction");

            //    System.out.println(quality);


                Alternative user0 = new Alternative(username);

                user0.addCriteriaValue(criteriaQuality,quality);
                user0.addCriteriaValue(criteriaSpeed,speed);
                user0.addCriteriaValue(criteriaAttitude,attitude);
                user0.addCriteriaValue(criteriaQuantity,quantity);
                user0.addCriteriaValue(criteriaInfluence,influence);
                user0.addCriteriaValue(criteriaSatisfaction,satisfaction);

                topsis.addAlternative(user0);
                //对每个对象的指标添加值
            }

        }

        try {
            Alternative result = topsis.calculateOptimalSolution();
        //    System.out.println("The optimal solution is: " + result.getName());
        //    System.out.println("The optimal solution score is: " + result.getCalculatedPerformanceScore());

        } catch (TopsisIncompleteAlternativeDataException e) {
            System.err.println(e.getMessage());
        }

        for (Alternative alternative : topsis.getAlternatives()){
            this.UpdateResult(alternative.getName(),alternative.getCalculatedPerformanceScore());
//            System.out.println(alternative.getName());
//            System.out.println(alternative.getCalculatedPerformanceScore());
        }

//        for(int i=0;i<10;i++){
//            Alternative user0 = new Alternative();
//            user0.addCriteriaValue(criteriaQuality,250);
//            user0.addCriteriaValue(criteriaSpeed,250);
//            user0.addCriteriaValue(criteriaAttitude,250);
//            user0.addCriteriaValue(criteriaQuantity,250);
//            user0.addCriteriaValue(criteriaInfluence,250);
//            user0.addCriteriaValue(criteriaSatisfaction,250);
//
//            topsis.addAlternative(user0);
//
//        }




//        Alternative user11 = new Alternative();
//        user11.addCriteriaValue(criteriaQuality,250);
//        user11.addCriteriaValue(criteriaSpeed,250);
//        user11.addCriteriaValue(criteriaAttitude,250);
//        user11.addCriteriaValue(criteriaQuantity,250);
//        user11.addCriteriaValue(criteriaInfluence,250);
//        user11.addCriteriaValue(criteriaSatisfaction,250);
//
//        Alternative user12 = new Alternative();
//        user11.addCriteriaValue(criteriaQuality,250);
//        user11.addCriteriaValue(criteriaSpeed,250);
//        user11.addCriteriaValue(criteriaAttitude,250);
//        user11.addCriteriaValue(criteriaQuantity,250);
//        user11.addCriteriaValue(criteriaInfluence,250);
//        user11.addCriteriaValue(criteriaSatisfaction,250);
//
//        Alternative user13 = new Alternative();
//        user11.addCriteriaValue(criteriaQuality,250);
//        user11.addCriteriaValue(criteriaSpeed,250);
//        user11.addCriteriaValue(criteriaAttitude,250);
//        user11.addCriteriaValue(criteriaQuantity,250);
//        user11.addCriteriaValue(criteriaInfluence,250);
//        user11.addCriteriaValue(criteriaSatisfaction,250);
//
//        Alternative user14 = new Alternative();
//
//
//        Alternative user15 = new Alternative();
//
//
//        Alternative user16 = new Alternative();
//
//
//        Alternative user17 = new Alternative();
//
//
//        Alternative user18 = new Alternative();
//
//
//        Alternative user19 = new Alternative();
//
//
//        Alternative user110 = new Alternative();
//
//
//
//        Topsis topsis = new Topsis();
//        topsis.addAlternative(user11);
//        topsis.addAlternative(user12);
//        topsis.addAlternative(user13);
//        topsis.addAlternative(user14);
//        topsis.addAlternative(user15);
//        topsis.addAlternative(user16);
//        topsis.addAlternative(user17);
//        topsis.addAlternative(user18);
//        topsis.addAlternative(user19);
//        topsis.addAlternative(user110);
//
//        System.out.println(user11.getCalculatedPerformanceScore());
//        System.out.println(user12.getCalculatedPerformanceScore());
//        System.out.println(user13.getCalculatedPerformanceScore());
//        System.out.println(user14.getCalculatedPerformanceScore());
//        System.out.println(user15.getCalculatedPerformanceScore());
//        System.out.println(user16.getCalculatedPerformanceScore());
//        System.out.println(user17.getCalculatedPerformanceScore());
//        System.out.println(user18.getCalculatedPerformanceScore());
//        System.out.println(user19.getCalculatedPerformanceScore());
//        System.out.println(user110.getCalculatedPerformanceScore());

    }



}
