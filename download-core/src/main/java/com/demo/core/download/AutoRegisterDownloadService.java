package com.demo.core.download;

import com.demo.core.download.domain.RegisterServiceParam;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @className: RegisterDownloadService
 * @description: 监听服务启动完成后，注册下载服务到服务中心
 * @author: th_legend
 * @date: 2021/7/19
 **/
public class AutoRegisterDownloadService implements CommandLineRunner {

    @Value("${spring.application.name}")
    private String applicationName;

    @Override
    public void run(String... args) throws Exception {
        ExecutorService executor = new ThreadPoolExecutor(1, 1,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>());
        executor.execute(() -> {
            List<DownloadService> exceptionService = new ArrayList<>();
            try {
                Thread.sleep(20000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Map<String, DownloadService> downloadServices = DownloadUtil.getDownloadServices();
            if (downloadServices.size() > 0) {
                Collection<DownloadService> services = downloadServices.values();
                for (DownloadService service : services) {
                    System.out.println("---RegisterDownloadService----" + service.requestMethod() + "------" + service.name() + "------" + service.requestUrl());
                    try {
                        doRegisterDownloadService(service);
                    } catch (Exception e) {
                        e.printStackTrace();
                        exceptionService.add(service);
                    }
                }
            }
            // 异常处理
            // 重试次数三次
            int retry = 3;
            int total = retry + 1;
            long intervalRetryTime = 30000L;
            while (exceptionService.size() > 0) {
                if (retry <= 0) {
                    break;
                }
                long sleepTime = (total - retry) * intervalRetryTime;
                try {
                    Thread.sleep(sleepTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                for (int i = exceptionService.size() - 1; i >= 0; i--) {
                    DownloadService downloadService = exceptionService.get(i);
                    try {
                        System.out.println("---Retry:::RegisterDownloadService----" + downloadService.requestMethod() + "------" + downloadService.name() + "------" + downloadService.requestUrl());
                        doRegisterDownloadService(downloadService);
                        exceptionService.remove(i);
                    } catch (Exception e) {

                    }
                }
                retry--;
            }

        });
        executor.shutdown();
    }

    /**
     * 注册下载服务到下载中心
     *
     * @param service:
     * @return: void
     * @Author: th_legend
     * @Date: 2021/8/18
     **/
    private void doRegisterDownloadService(DownloadService service) {
        RegisterServiceParam registerModel = new RegisterServiceParam();
        registerModel.setName(service.name());
        registerModel.setDescription(service.description());
        registerModel.setRequestUrl(service.requestUrl());
        registerModel.setRequestMethod(service.requestMethod());
        registerModel.setApplicationName(this.applicationName);
        DownloadUtil.registerService(registerModel);
    }
}
