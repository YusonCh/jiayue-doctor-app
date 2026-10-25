package com.qys.im.dao;

import com.qys.im.pojo.Illness_Record;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Repository
public interface IllnessDao {

    List<Illness_Record> SelectIllnessReport(String username);

    List<Illness_Record> SelectIllnessReportByDoctor(String username);

    List<Illness_Record> SelectIllness(String name,String doctor);

    int InsertIllness(String username, String name, String doctor, Date date ,String illness,String end);

    int UpdateIllness(String reason,String medicine,String diet,String cure,int id);

    int SelectNumber();
}
