package com.demo.downloadserver;

import com.demo.core.download.EnableDownLoadServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @className: DownlaodServerApplication
 * @description: TODO 类描述
 * @author: th_legend
 * @date: 2021/7/15
 **/
@EnableDiscoveryClient
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableFeignClients(basePackages ={"com.demo"})
@ComponentScan(basePackages = {"com.demo"})
@EnableDownLoadServer
public class DownloadServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(DownloadServerApplication.class,args);
    }
}
