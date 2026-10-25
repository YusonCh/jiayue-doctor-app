package com.qys.im.service;

import com.qys.im.dao.UserDoctorDao;
import com.qys.im.dao.UserPatientDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserDoctorService {

    @Autowired
    private UserDoctorDao userDoctorDao;

    public String signupByUserName(String username,String password) {

        userDoctorDao.signupByUserName(username,password);
        return "注册成功";
    }

    public List<String> SelectAllDoctorInformation(){
        return userDoctorDao.SelectAllDoctorInformation();
    }

    public List<Map<String,String>> SelectDoctor(String username){
        return userDoctorDao.SelectDoctor(username);
    }

    public List<String> SelectDoctorFamous(String username){
        return userDoctorDao.SelectDoctorFamous(username);
    }

    public List<String> SelectDoctorNmae(String name){
        return userDoctorDao.SelectDoctorName(name);
    }

    public List<String> SelectDoctorDepartment(String username){
        return userDoctorDao.SelectDoctorDepartment(username);
    }

    public int InsertDoctor(String user_name,String password,String level,String department,String name,String hospital){
        return userDoctorDao.InsertDoctor(user_name, password, level, department, name, hospital);
    }

    public int UpdateResume(String username,String resume){
        return userDoctorDao.UpdateResume(username,resume);
    }

    public List<Map<String,String>> SelectSystemDoctor(){
        return userDoctorDao.SelectSystemDoctor();
    }

    public int UpdateSystemDoctor(String password,String name,String hospital,String department,String username,String level){
        return userDoctorDao.UpdateSystemDoctor(password, name, hospital, department,username,level);
    }

    public int DeleteDoctor(String username){
        return userDoctorDao.DeleteDoctor(username);
    }

    public List<Map<String,String >> SelectUserPatient(String username){
        return userDoctorDao.SelectUserPatient(username);
    }

    public int updateConnect(String username,String connect){
        return userDoctorDao.updateConnect(username,connect);
    }

    public int updateName(String username,String name){
        return userDoctorDao.updateName(username,name);
    }

    public int updateImage(String image,String name){
        return userDoctorDao.updateImage(image, name);
    }
}
