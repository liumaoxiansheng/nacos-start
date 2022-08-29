package com.demo.downloadcenter.controller.call;

import com.demo.core.download.DownloadUtil;
import com.demo.core.download.domain.CallbackParam;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * @className: CallBackController
 * @description: 回调
 * @author: th_legend
 * @date: 2021/7/15
 **/
@RestController
@RequestMapping("callback")
public class CallBackController {

    @GetMapping("/noticeResult")
    public void noticeResult(@RequestParam String result){
        System.out.println("callback：：noticeResult()......");
        System.out.println("callback：：DownloadUtil.getTaskId() = " + DownloadUtil.getTaskId());
        System.out.println("callback：：DownloadUtil.getUserId() = " + DownloadUtil.getUserId());
        System.out.println("callback：：result = " + result);
    }

    @PostMapping("/postResult")
    public void postResult(@RequestBody CallbackParam callbackParam){
        System.out.println("callback.postResult()...."+callbackParam);
        HttpServletRequest request = DownloadUtil.getRequest();
        Enumeration<String> headerNames = request.getHeaderNames();
        if (headerNames!=null) {
            while (headerNames.hasMoreElements()) {
                String name = headerNames.nextElement();
                System.out.println("header : "+name+" = "+request.getHeader(name));
            }
        }
    }
}
