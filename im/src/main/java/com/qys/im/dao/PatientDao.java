package com.qys.im.dao;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientDao {

    List<String> SelectPatient(String username,String name);


}
