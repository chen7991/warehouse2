package com.casic.warehouse2;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.casic.warehouse2.dao")
public class Warehouse2Application {

    public static void main(String[] args) {
        SpringApplication.run(Warehouse2Application.class, args);
    }

}
