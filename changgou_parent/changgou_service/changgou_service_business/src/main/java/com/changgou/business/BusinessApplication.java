package com.changgou.business;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@EnableEurekaClient
@MapperScan(basePackages = {"com.changgou.business.dao"})
public class BusinessApplication {
    public static void main(String[] args) {
        SpringApplication.run(BusinessApplication.class);
    }
}
