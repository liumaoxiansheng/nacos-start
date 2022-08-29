package com.demo.downloadcenter.controller;

import cn.hutool.core.util.StrUtil;
import com.demo.core.download.DownloadUtil;
import com.demo.core.download.domain.RegisterServiceParam;
import com.demo.core.download.domain.ServicesConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

/**
 * @className: DownloadController
 * @description: TODO 类描述
 * @author: th_legend
 * @date: 2021/7/15
 **/
@RestController
@RequestMapping("/download")
public class DownloadController {

    private ExecutorService executor = new ThreadPoolExecutor(20, 20,
            0L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<Runnable>(5000));

    private static Map<String, Map<String, String>> servers = new ConcurrentHashMap<>(8);

    static {
        HashMap<String, String> server = new HashMap<>();
        server.put("serverName", "com.demo.core.download-server");
        server.put("url", "/com/demo/core/download/getFileUrl");

        HashMap<String, String> server2 = new HashMap<>();
        server2.put("serverName", "com.demo.core.download-server");
        server2.put("url", "/com/demo/core/download/postJson");

        HashMap<String, String> server3 = new HashMap<>();
        server3.put("serverName", "com.demo.core.download-server");
        server3.put("url", "/com/demo/core/download/postForm");
        servers.put("1", server);
        servers.put("2", server2);
        servers.put("3", server3);
    }

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/downloadFile")
    public String downloadFile(@RequestParam String serviceName,String userId) {
        System.out.println("downloadFile()....");
        HttpServletRequest request = DownloadUtil.getRequest();
        String queryString = request.getQueryString();
        System.out.println("queryString = " + queryString);
        String restUrl = "http://%s/%s";

        if (StrUtil.isNotBlank(queryString)) {
            restUrl = restUrl +"?"+ queryString;
        }
        List<ServiceInstance> instances = new ArrayList<>();
        RegisterServiceParam service = ServicesConstant.getService(serviceName);
        if (service != null) {
            // 请求地址处理
            String url = service.getRequestUrl();
            String serverName = service.getApplicationName();
            instances = discoveryClient.getInstances(serverName);
            if (CollectionUtils.isEmpty(instances)) {
                return "ERROR::::error serverName :::" + serverName;
            }
          final  String finalUrl= String.format(restUrl,serverName,url);
            System.out.println("restUrl = " + finalUrl);
            // 请求头处理
            HttpHeaders httpHeaders = DownloadUtil.transferHttpHeaders();
            httpHeaders.add("downloadTaskId", serviceName);
            httpHeaders.add("userId",userId);
            // 请求体处理并转发请求
            HttpEntity<Object> httpEntity = new HttpEntity<>(DownloadUtil.handlerBodyData(), httpHeaders);
            try {
                executor.execute(()->{
                    restTemplate.exchange(finalUrl, HttpMethod.valueOf(request.getMethod()), httpEntity, String.class);
                });
            } catch (RestClientException e) {
                return "ERROR:::error server url is " + finalUrl;
            } catch (IllegalArgumentException e) {
                return "ERROR:::error param.";
            }
            return "File is Downloading...";
        }
        return "ERROR::sever is not exist";
    }


    @PostMapping("/registerService")
    public String registerService(@RequestBody RegisterServiceParam param) {
        ServicesConstant.addService(param.getName(), param);
        System.out.println("register service " + param.getName() + " success");
        System.out.println("param.getApplicationName() = " + param.getApplicationName());
        return "register service " + param.getName() + " success";
    }
}
