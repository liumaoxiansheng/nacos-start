package com.study.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.study.test.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @className: TestController
 * @description: TODO 类描述
 * @author: th_legend
 * @date: 2021/7/9
 **/
@RestController
public class TestController {


    @Autowired
    private TestService service;

    @GetMapping(value = "/hello/{name}")
    @SentinelResource("/sayHello")
    public String apiHello(@PathVariable String name) {
        System.out.println("in.........");
        return service.sayHello(name);
    }
}
