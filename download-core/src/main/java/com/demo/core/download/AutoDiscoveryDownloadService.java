package com.demo.core.download;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Controller;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * @ClassName: AutoDiscoveryDownloadService
 * @Description: 自动发现下载服务
 * @Author: th_legend
 * @Date: 2021/8/18
 **/
public class AutoDiscoveryDownloadService implements ApplicationContextAware {
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        // 收集扫描下载服务
        Map<String, Object> annotations = applicationContext.getBeansWithAnnotation(Controller.class);
        if (annotations!=null) {
            for (Object value : annotations.values()) {
                for (Method method : value.getClass().getMethods()) {
                    DownloadService annotation = method.getAnnotation(DownloadService.class);
                    if (annotation!=null) {
                        if (DownloadUtil.getDownloadServices().get(annotation.name())!=null) {
                            throw new RuntimeException(("this DownloadService name is "+annotation.name()+" has exist,please rename"));
                        }
                        DownloadUtil.getDownloadServices().put(annotation.name(),annotation);
                    }
                }
            }
        }
    }
}
