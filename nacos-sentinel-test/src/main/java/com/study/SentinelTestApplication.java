package com.study;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @className: SentinelTestApplication
 * @description: TODO 类描述
 * @author: th_legend
 * @date: 2021/7/9
 **/
@SpringBootApplication
@EnableFeignClients
public class SentinelTestApplication {
    public static void main(String[] args) {
        SpringApplication.run(SentinelTestApplication.class,args);
    }
}
