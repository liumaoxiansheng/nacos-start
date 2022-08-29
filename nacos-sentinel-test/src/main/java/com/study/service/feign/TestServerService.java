package com.study.service.feign;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.study.service.feign.fallback.TestServerServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @className: TestServerService
 * @description: feign test
 * @author: th_legend
 * @date: 2021/7/12
 **/
@FeignClient(value = "test-provider-server",fallback = TestServerServiceFallback.class,configuration = TestServerServiceFallback.class)
public interface TestServerService {
    @GetMapping("/testFeignService")
    String getFeignService(@RequestParam String param);
}
