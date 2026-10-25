package com.qys.im;

import com.qys.im.netty.CoordinationNettyServer;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.qys.im.netty.CoordinationNettyServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.qys")
@MapperScan("com.qys.im.dao")
public class ImApplication {

    public static void main(String[] args) {
        SpringApplication.run(ImApplication.class, args);

        try {
            new CoordinationNettyServer(8804).start();
        } catch (Exception e) {
            System.out.println("NettyServerError:" + e.getMessage());
        }
    }

}
