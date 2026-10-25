package com.qys.im.controller;


import com.qys.im.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/patient")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @GetMapping("/GetPatient")
    public List<String> SelectPatient(String username, String name){

        return patientService.SelectPatient(username, name);
    }

    @GetMapping("/getPatient")
    public List<String> SelectPatient(String username){
        System.out.println(username);
        return patientService.selectPatient(username);
    }
}
