package com.qys.im.dao;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface UserDoctorDao {

    int signupByUserName(String username,String password);

    List<String> SelectAllDoctorInformation();

    List<Map<String,String>> SelectDoctor(String username);

    List<String> SelectDoctorFamous(String username);

    List<String> SelectDoctorDepartment(String department);

    List<String> SelectDoctorName(String name);

    int InsertDoctor(String user_name,String password,String level,String department,String name,String hospital);

    int UpdateResume(String username,String resume);

    List<Map<String,String>> SelectSystemDoctor();

    int UpdateSystemDoctor(String password,String name,String hospital,String department,String username,String level);

    int DeleteDoctor(String username);

    List<Map<String,String >> SelectUserPatient(String username);

    int updateConnect(String username,String connect);

    int updateName(String username,String name);

    int updateImage(String username,String image);


}
