package com.study.testserver.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @className: TestProviderController
 * @description: TODO 类描述
 * @author: th_legend
 * @date: 2021/7/8
 **/
@RestController
public class TestProviderController {

    @GetMapping("/testProvider")
    public String testProvider(){
        return "hello testProvider";
    }

    @GetMapping("/testFeignService")
    public String testFeignService(@RequestParam String param){
        System.out.println("testFeignService()..."+param);
        return "testFeignService()..."+param;
    }
}
