package com.qys.im.service;

import com.qys.im.dao.DepartmentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentDao departmentDao;

    public List<String> SelectDepartment(){
        return departmentDao.SelectDepartment();
    }

    public List<String> SelectHospital(){
        return departmentDao.SelectHospital();
    }

    public List<String> SelectLevel(){
        return departmentDao.SelectLevel();
    }


}
