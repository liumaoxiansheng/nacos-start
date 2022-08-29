package com.study.service.feign.fallback;

import com.study.service.feign.TestServerService;

/**
 * @className: TestServerServiceFallback
 * @description: TODO 类描述
 * @author: th_legend
 * @date: 2021/7/12
 **/
public class TestServerServiceFallback implements TestServerService {
    @Override
    public String getFeignService(String param) {
        System.out.println("localFallbackService....");
        return "localFallbackService...."+param;
    }
}
