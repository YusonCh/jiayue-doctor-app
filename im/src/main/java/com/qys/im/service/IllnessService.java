package com.qys.im.service;

import com.qys.im.dao.IllnessDao;
import com.qys.im.pojo.Illness_Record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class IllnessService {

    @Autowired
    private IllnessDao illnessDao;

    public List<Illness_Record> SelectIllnessReport(String username){
        return illnessDao.SelectIllnessReport(username);
    }

    public List<Illness_Record> SelectIllnessReportByDoctor(String username){
        return illnessDao.SelectIllnessReportByDoctor(username);
    }

    public List<Illness_Record> SelectIllness(String name,String doctor){
        return illnessDao.SelectIllness(name,doctor);
    }

    public int InsertIllness(String name, String username, String doctor, Date date,String illness,String end){
        return illnessDao.InsertIllness(name, username, doctor, date,illness,end);
    }

    public int UpdateIllness(String reason,String medicine,String diet,String cure,int id){
        return illnessDao.UpdateIllness(reason, medicine, diet, cure, id);
    }

    public int SelectNumber(){
        return illnessDao.SelectNumber();
    }
}
