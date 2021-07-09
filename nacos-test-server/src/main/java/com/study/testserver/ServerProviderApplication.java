package com.study.testserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @className: ServerProviderApplication
 * @description: TODO 类描述
 * @author: th_legend
 * @date: 2021/7/8
 **/
@SpringBootApplication
@EnableDiscoveryClient
public class ServerProviderApplication {
    public static void main(String[] args) {
        System.setProperty("nacos.standalone", "true");
        SpringApplication.run(ServerProviderApplication.class,args);
    }
}
