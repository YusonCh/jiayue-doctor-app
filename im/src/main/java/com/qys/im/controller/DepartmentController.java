package com.qys.im.controller;

import com.qys.im.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/department")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @GetMapping("/selectDepartment")
    List<String> SelectDepartment(){
        return departmentService.SelectDepartment();
    }

    @GetMapping("/selectHospital")
    List<String> SelectHospital(){
        return departmentService.SelectHospital();
    }

    @GetMapping("/selectLevel")
    List<String> SelectLevel(){
        return departmentService.SelectLevel();
    }
}
