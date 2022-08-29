package com.demo.downloadserver.controller;

import com.demo.core.download.DownloadExecutor;
import com.demo.core.download.DownloadService;
import com.demo.core.download.DownloadUtil;
import com.demo.core.download.domain.CallbackParam;
import com.demo.core.download.domain.TestPostParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @className: DownloadController
 * @description: TODO 类描述
 * @author: th_legend
 * @date: 2021/7/15
 **/
@RestController
@RequestMapping("/download")
public class DownloadController {

    @Autowired
    private DownloadExecutor downloadExecutor;

    @GetMapping("getFileUrl")
    @DownloadService(name = "download-server::getFileUrl", requestUrl = "/download/getFileUrl", requestMethod = "get")
    public void downloadFile(@RequestParam String ftlName){
        String taskId = DownloadUtil.getTaskId();
        HttpHeaders httpHeaders = DownloadUtil.transferHttpHeaders();
        httpHeaders.add("callHeader","callTest");
        System.out.println("Download:::Start....");
        // 模拟回调
        DownloadUtil.callback("Download:::Start", httpHeaders);
        try {
             new Thread(() -> {
                DownloadUtil.callback("Download:::50%", httpHeaders);
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                DownloadUtil.callback("Download:::100%:::Download Url is https://www.xxx.cn/abc.doc", httpHeaders);
                 System.out.println("Download:::End....");
            }).start();
        } catch (Exception e) {
            e.printStackTrace();
          // return  "downloadServer down file exception";
        }

       // return "downloadServer down file";
    }

//    @GetMapping("getFileUr2")
//    @DownloadService(name = "downloadFile",url = "/com.demo.core.download/downloadFile",method = "get")
//    public String downloadFile2(@RequestParam String id,@RequestParam String name){
//        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
//        String taskId = request.getHeader("downloadTaskId");
//        System.out.println("taskId:::"+taskId);
//        HttpHeaders httpHeaders = DownloadUtil.transferHttpHeaders();
//        httpHeaders.add("callHeader","callTest");
//
//        // 模拟回调
//        downloadExecutor.callback("1", httpHeaders);
//        try {
//            Thread thread = new Thread(() -> {
//
//                //for (int i = 0; i < 1; i++) {
//                downloadExecutor.callback("1", httpHeaders);
//                try {
//                    Thread.sleep(3000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                DownloadUtil.callback("2", httpHeaders);
//                // }
//            });
//            thread.start();
//
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            return  "downloadServer down file exception";
//        }
//
//        return "downloadServer down file";
//    }

    @PostMapping("/postJson")
    @DownloadService(name = "postJson", requestUrl = "/download/postJson", requestMethod = "post")
    public String postJson(@RequestBody TestPostParam param){
         HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String taskId = request.getHeader("downloadTaskId");
        System.out.println("taskId:::"+taskId);
        CallbackParam callbackParam = new CallbackParam();
        callbackParam.setTaskId(param.getTaskId());
        callbackParam.setTotalCount(param.getTotalCount());

        HttpHeaders httpHeaders = DownloadUtil.transferHttpHeaders();
        httpHeaders.add("callHeader","callTest");
        try {
            new Thread(()->{
                for (int i = 0; i < 1; i++) {
                    DownloadUtil.postResult(callbackParam,httpHeaders);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();

        } catch (Exception e) {
            e.printStackTrace();
           return  "downloadServer down file exception";
        }

        return "downloadServer down file";
    }

    @PostMapping("/postForm")
    @DownloadService(name = "postForm", requestUrl = "/com/demo/core/download/postForm", requestMethod = "post")
    public String postForm(TestPostParam param){

        String taskId = DownloadUtil.getTaskId();
        System.out.println("taskId:::"+taskId);
        CallbackParam callbackParam = new CallbackParam();
        callbackParam.setTaskId(param.getTaskId());
        callbackParam.setTotalCount(param.getTotalCount());

        HttpHeaders httpHeaders = DownloadUtil.transferHttpHeaders();
        httpHeaders.add("callHeader","callTest");
        try {
            new Thread(()->{
                for (int i = 0; i < 1; i++) {
                    DownloadUtil.postResult(callbackParam,httpHeaders);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();

        } catch (Exception e) {
            e.printStackTrace();
           return  "downloadServer down file exception";
        }

        return "downloadServer down file";
    }
}
