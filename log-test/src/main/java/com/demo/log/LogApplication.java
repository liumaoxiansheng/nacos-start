package com.demo.log;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @ClassName: LogApplication
 * @Description: TODO 类描述
 * @Author: th_legend
 * @Date: 2021/9/2
 **/
@SpringBootApplication
//@EnableScheduling
public class LogApplication {
    public static void main(String[] args) {
        SpringApplication.run(LogApplication.class,args);
    }
}
