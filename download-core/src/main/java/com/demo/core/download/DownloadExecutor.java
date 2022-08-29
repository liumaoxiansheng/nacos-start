package com.demo.core.download;

import com.demo.core.download.domain.CallbackParam;
import com.demo.core.download.domain.RegisterServiceParam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

/**
 * @className: DownloadExecutor
 * @description:  处理器
 * @author: th_legend
 * @date: 2021/7/15
 **/
@FeignClient(name = "download-center"/**,configuration ={FeignConfiguration.class}*/ )
public interface DownloadExecutor {


    /**
     * 回调接口
     *
     * @param result:
     * @return: java.lang.String
     * @author: th_legend
     * @date: 2021/7/15
     **/
    @GetMapping("/callback/noticeResult")
    public String callback(@RequestParam String result,@RequestHeader HttpHeaders httpHeaders);


    /**
     * 回调接口
     *
     * @param: callbackParam 回调参数
     * @return: java.lang.String
     * @author: th_legend
     * @date: 2021/7/15
     **/
    @PostMapping("/callback/postResult")
    public String postResult(@RequestBody CallbackParam callbackParam, @RequestHeader HttpHeaders header);


    /**
     * 注册下载中心服务
     *
     * @param:downloadService 下载中心服务参数
     * @return: java.lang.String
     * @author: th_legend
     * @date: 2021/7/15
     **/
    @PostMapping("/download/registerService")
    public String registerService(@RequestBody RegisterServiceParam downloadService);

}
