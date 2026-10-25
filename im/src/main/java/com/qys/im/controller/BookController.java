package com.qys.im.controller;

import com.qys.im.annotation.LoginUser;
import com.qys.im.pojo.User;
import com.qys.im.service.UserPatientService;
import com.qys.im.utils.ResponseUtils;
import org.apache.logging.log4j.util.PerformanceSensitive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/books")
public class BookController {

    @GetMapping("/{id}")
    public String getById(@PathVariable Integer id) {
        System.out.println("id -->" + id);
        return "hello springboot";
    }
}
