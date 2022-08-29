package com.demo.downloadcenter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @className: DownloadCenterApplication
 * @description: TODO 类描述
 * @author: th_legend
 * @date: 2021/7/15
 **/
@EnableDiscoveryClient
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class,scanBasePackages = "com.demo")
@EnableFeignClients
public class DownloadCenterApplication {
    public static void main(String[] args) {
        SpringApplication.run(DownloadCenterApplication.class,args);
    }
}
