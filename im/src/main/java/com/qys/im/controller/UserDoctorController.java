package com.qys.im.controller;

import com.qys.im.dao.UserDao;
import com.qys.im.service.UserDoctorService;
import com.qys.im.service.UserPatientService;
import com.qys.im.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/register_doctor")
public class UserDoctorController {
    @Autowired
    private UserDoctorService userDoctorService;

    @Autowired
    private UserService userService;

    @PostMapping("/signupByUserName")
    public String getUserByUserName(@RequestBody Map<String,String> map){
        String username = map.get("username");
        String password = map.get("password");
        //   System.out.println("username"+username);
        //   System.out.println("password"+password);
        userDoctorService.signupByUserName(username,password);
        return "注册成功";

    }

    @GetMapping("/SelectAllDoctor")
    public List<String> SelectAllDoctorInformation(){
            return userDoctorService.SelectAllDoctorInformation();
    }

    @GetMapping("/SelectDoctor")
    public List<Map<String,String>> SelectDoctor(@RequestParam("username") String username){
//        String username = map.get("username");
    //    System.out.println(username);
    //    System.out.println(userDoctorService.SelectDoctor(username));
        List<Map<String,String>> SelectDoctor = userDoctorService.SelectDoctor(username);
        int length = SelectDoctor.size();
        for(int i=0;i<length;i++){
            Map<String,String> map = SelectDoctor.get(i);
            String temp = map.get("level");
            switch(temp) {
                case "1":
                    map.put("level","医师");
                    break;
                case "2":
                    map.put("level","主治医师");
                    break;
                case "3":
                    map.put("level","副主任医师");
                    break;
                case "4":
                    map.put("level","主任医师");
                    break;
            }
            SelectDoctor.set(i,map);
        }
        return SelectDoctor;
    }

    @GetMapping("/SelectDoctorFamous")
    public List<String> SelectDoctorFamous(String username){
        return userDoctorService.SelectDoctorFamous(username);
    }

    @GetMapping("/SelectDoctorDepartment")
    public List<String> SelectDoctorDepartment(String department){
        System.out.println(department);
        return userDoctorService.SelectDoctorDepartment(department);
    }

    @GetMapping("/SelectDoctorName")
    public List<String> SelectDoctorName(String name){
        System.out.println(name);
        return userDoctorService.SelectDoctorNmae(name);
    }

    @PostMapping("/InsertDoctor")
    public int InsertUser(@RequestBody Map<String,Object> map){
        String user_name = (String) map.get("username");
        String password = (String) map.get("password");
        String level = (String) map.get("level");
        String department = (String) map.get("department");
        String name = (String) map.get("name");
        String hospital = (String) map.get("hospital");

        return this.InsertDoctor(user_name,password,level,department,name,hospital);
    }

    public int InsertDoctor(String user_name,String password,String connect,String department,String level,String hospital){
        return userDoctorService.InsertDoctor(user_name,password,connect,department,level,hospital);
    }

    @PostMapping("/UpdateResume")
    public int UpdateResume(@RequestBody Map<String,String> map){
        String username = map.get("username");
        String resume = map.get("resume");

        return userDoctorService.UpdateResume(username,resume);
    }

    @GetMapping("/SelectSystemDoctor")
    public List<Map<String,String>> SelectSystemDoctor() {
        List<Map<String,String>> selectSystem = userDoctorService.SelectSystemDoctor();
        int length = selectSystem.size();
        for (int i = 0; i < length; i++) {
            Map<String, String> map = userDoctorService.SelectSystemDoctor().get(i);
            String temp = map.get("level");
            switch (temp) {
                case "1":
                    map.put("level", "医师");
                    break;
                case "2":
                    map.put("level", "主治医师");
                    break;
                case "3":
                    map.put("level", "副主任医师");
                    break;
                case "4":
                    map.put("level", "主任医师");
                    break;
            }
            selectSystem.set(i, map);
        }
        return selectSystem;
    }

    @PostMapping("/UpdateSystemDoctor")
    public  int UpdateSystemDoctor(@RequestBody  Map<String,String> map){
        String password = (String)map.get("password");
        String name = (String)map.get("name");
        String hospital = (String)map.get("hospital");
        String department = (String)map.get("department");
        String username = (String) map.get("username");
        String level0 = (String) map.get("level");
        String level = "";
        if(level0!=null){
            switch (level0) {
                case "医师":
                    level = "1";
                    break;
                case "主治医师":
                    level = "2";
                    break;
                case "副主任医师":
                    level = "3";
                    break;
                case "主任医师":
                    level = "4";
                    break;
            }
        }

        System.out.println(username);

        return userDoctorService.UpdateSystemDoctor(password,name,hospital,department,username,level);
    }

    @PostMapping("/DeleteDoctor")
    public int DeleteDoctor(@RequestBody Map<String,String> map){
        String username = (String)map.get("username");
        return userDoctorService.DeleteDoctor(username);
    }

    @GetMapping("/SelectPatient")
    public List<Map<String,String>> SelectUserPatient(String username){
        return userDoctorService.SelectUserPatient(username);
    }

    @PostMapping("/updateConnect")
    public int updateConnect(@RequestBody Map<String,String> map){
        String username = (String)map.get("username");
        String connect = (String) map.get("connect");

        System.out.println(username);

        return userDoctorService.updateConnect(username,connect);
    }

    @PostMapping("/updateName")
    public int updateName(@RequestBody Map<String,String> map){
        String username = (String)map.get("username");
        String name = (String) map.get("name");

        //System.out.println(username);

        return userDoctorService.updateName(username,name);
    }

    @PostMapping("/updateImage")
    public int updateImage(@RequestBody Map<String,String> map){
        String username = (String)map.get("username");
        String image = (String) map.get("image");

        //System.out.println(username);

        return userDoctorService.updateImage(username,image);
    }
}
