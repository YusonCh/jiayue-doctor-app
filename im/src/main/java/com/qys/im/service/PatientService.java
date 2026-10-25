package com.qys.im.service;

import com.qys.im.dao.HealthDao;
import com.qys.im.dao.PatientDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PatientService {

    @Autowired
    private PatientDao patientDao;

    @Autowired
    private HealthDao healthDao;


    public List<String> SelectPatient(String username, String name){
        return patientDao.SelectPatient(username, name);
    }

    public List<String> selectPatient(String username){
        return healthDao.selectPatient(username);
    }
}
