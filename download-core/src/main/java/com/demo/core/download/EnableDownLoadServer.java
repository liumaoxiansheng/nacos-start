package com.demo.core.download;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @ClassName: EnableDownLoadServer
 * @Description: 开启支持下载中心服务
 * @Author: th_legend
 * @Date: 2021/8/17
 **/
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({AutoRegisterDownloadService.class,AutoDiscoveryDownloadService.class})
public @interface EnableDownLoadServer {
    boolean enable() default true;
}
