package com.demo.core.download;

import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @className: DownloadService
 * @description: 下载服务
 * @author: th_legend
 * @date: 2021/7/19
 **/
@Target({ ElementType.METHOD })//注解用在方法上
@Retention(RetentionPolicy.RUNTIME)//VM将在运行期也保留注释，因此可以通过反射机制读取注解的信息
@Component
public @interface DownloadService {

    /**
     * 服务名称，必须保证唯一,建议采用${spring.application.name}::url
     **/
    String name();

    /**
     * url，必须保证正确的url(可访问):contextPath+controllerMapping+methodMapping
     **/
    String requestUrl();

    /**
     * 请求方法类型，预留，暂时不用，目前支持GET和POST
     **/
    String requestMethod();

    /**
     * 当前微服务名称
     **/
   // String applicationName();

    /**
     * 服务描述
     **/
    String description() default " ";
}
