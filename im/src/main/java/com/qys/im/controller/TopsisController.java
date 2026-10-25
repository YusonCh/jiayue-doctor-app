package com.qys.im.controller;

import com.qys.im.Topsis.topsis.Alternative;
import com.qys.im.Topsis.topsis.Topsis;
import com.qys.im.Topsis.topsis.TopsisIncompleteAlternativeDataException;
import com.qys.im.service.TopsisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.*;
import com.qys.im.Topsis.*;


@RestController
@RequestMapping("/Topsis")
public class TopsisController {


    @Autowired
    public TopsisService topsisService;

    private Topsis topsis;

    @PostMapping("/InsertEvaluation")
    public String InsertEvaluation(@RequestBody Map<String,Object> map){
//        String user_name = (String)map.get("user_name");
//
//        int answer_quality0 = (int) map.get("answer_quality");
//        int answer_speed0=(int) map.get("answer_quality");
//        int answer_attitude0=(int) map.get("answer_quality");
//        int answer_influence0=(int) map.get("answer_quality");
//        int answer_satisfaction0=(int) map.get("answer_quality");
//        int answer_quantity0=(int) map.get("answer_quality");
//
//        double answer_quality = answer_quality0;
//        double answer_speed=answer_speed0;
//        double answer_attitude=answer_attitude0;
//        double answer_quantity=answer_quantity0;
//        double answer_influence=answer_influence0;
//        double answer_satisfaction=answer_satisfaction0;
//
//        System.out.println(user_name);
//
//        topsisService.InsertEvaluation(user_name, answer_quality, answer_speed, answer_attitude, answer_quantity, answer_influence, answer_satisfaction);
//        return "成功插入";

        topsisService.InsertEvaluation();
        return "成功插入";
    }

    @GetMapping("/test")
    public void Test(){
        try {
            topsisService.testMobileMCDM();
        } catch (TopsisIncompleteAlternativeDataException e) {
            System.err.println(e.getMessage());
        }


    }

}
