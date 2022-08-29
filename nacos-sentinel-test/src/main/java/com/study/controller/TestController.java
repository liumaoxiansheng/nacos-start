package com.study.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.study.config.MyBlockHandler;
import com.study.service.feign.TestServerService;
import com.study.service.feign.fallback.TestServerServiceFallback;
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

    @Autowired(required = true)
    private TestServerService serverService;

    @GetMapping(value = "/hello/{name}")
    public String apiHello(@PathVariable String name) {
        System.out.println("in.........");
        return service.sayHello(name);
    }

    @GetMapping(value = "/testFeign/{param}")
    @SentinelResource(value = "/testFeign/{param}",blockHandler ="localFallback" )// 降级
    public String testFeign(@PathVariable String param) {
        System.out.println("testFeign...Controller......");
        return serverService.getFeignService(param);
    }


    @GetMapping(value = "/testLimit/{param}")
    @SentinelResource(value = "/testLimit/{param}",blockHandlerClass = MyBlockHandler.class,blockHandler = "exceptionHandler1")// 降级
    public String testLimit(@PathVariable String param) {
        System.out.println("testFeign...Controller......");
        return serverService.getFeignService(param);
    }



    public String localFallback(String param, BlockException e){
        System.out.println("localFallback...Controller......");
         return "localFallback...."+param;
    }




}
