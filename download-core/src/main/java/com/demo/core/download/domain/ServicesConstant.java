package com.demo.core.download.domain;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @className: ServicesConstant
 * @description: TODO 类描述
 * @author: th_legend
 * @date: 2021/7/19
 **/
public class ServicesConstant {

    private static Map<String, RegisterServiceParam> serviceMap=new ConcurrentHashMap<>();

    public static void addService(String key,RegisterServiceParam service){
        serviceMap.put(key,service);
    }

    public static boolean removeService(String key,RegisterServiceParam service){
        return serviceMap.remove(key,service);
    }

    public static RegisterServiceParam getService(String key){
       return serviceMap.get(key);
    }
}
