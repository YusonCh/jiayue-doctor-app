package com.qys.im.dao;

import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface TopsisDao {
    int InsertEvaluation(String user_name,int answer_quality,int answer_speed,int answer_attitude,int answer_quantity,int answer_influence,int answer_satisfaction);

    List<Map<String,Integer>> SelectEvaluation(String username);

    int UpdateResult(String username,double result);

    int UpdateEvaluation(String username,int answer_quality,int answer_speed,int answer_attitude,int answer_quantity,int answer_influence,int answer_satisfaction);


}
