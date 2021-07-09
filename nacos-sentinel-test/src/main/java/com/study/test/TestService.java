package com.study.test;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.springframework.stereotype.Service;

/**
 * @className: TestService
 * @description: TODO 类描述
 * @author: th_legend
 * @date: 2021/7/9
 **/
@Service
public class TestService {


    public String sayHello(String name) {
        return "Hello, " + name;
    }
}
