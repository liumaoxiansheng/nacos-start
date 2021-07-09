package com.study.testconsumer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @className: TestController
 * @description: TODO 类描述
 * @author: th_legend
 * @date: 2021/7/8
 **/
@RestController
public class TestController {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${redis.version}")
    private String  version;

    @Value("${mysql.version}")
    private String  mysqlVersion;

    @GetMapping("/test")
    public String test() {
        return "hello nacos";
    }

    @GetMapping("/testConsumer")
    public String testConsumer() {
        String forObject = restTemplate.getForObject("http://test-provider-server/testProvider", String.class);
        if (null != forObject) {
            return forObject;
        }

        return "hello nacos";
    }

    @GetMapping("/getConfig")
    public String getConfig() {

        return version;
    }

    @GetMapping("/getConfig2")
    public String getConfig2() {

        return mysqlVersion;
    }
}
