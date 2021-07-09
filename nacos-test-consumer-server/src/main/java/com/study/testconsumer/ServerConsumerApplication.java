package com.study.testconsumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @className: ServerConsumerApplicaation
 * @description: TODO 类描述
 * @author: th_legend
 * @date: 2021/7/8
 **/
@SpringBootApplication
@EnableDiscoveryClient
public class ServerConsumerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServerConsumerApplication.class,args);
    }

    @LoadBalanced
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
