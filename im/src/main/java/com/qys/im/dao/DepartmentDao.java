package com.qys.im.dao;

import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface DepartmentDao {

    List<String> SelectDepartment();

    List<String> SelectHospital();

    List<String> SelectLevel();
}
