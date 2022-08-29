package com.demo.core.download;

import cn.hutool.json.JSONUtil;
import com.demo.core.download.domain.CallbackParam;
import com.demo.core.download.domain.RegisterServiceParam;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @className: DownloadUtil
 * @description: 请求数据处理，此工具在一次请求未完成的过程用，仅对单次请求有效
 * @author: th_legend
 * @date: 2021/7/15
 **/

public class DownloadUtil {

    public final static String CONTENT_TYPE_JSON = "application/json";
    public final static String CONTENT_TYPE = "content-type";
    public final static String CONTENT_LENGTH = "content-length";

    public final static List<String> EXCLUDE_HEADERS = Arrays.asList(CONTENT_LENGTH);

    /**
     * 收集的下载服务
     **/
    private static Map<String, DownloadService> downloadServices = new ConcurrentHashMap<>(8);


    private DownloadUtil() {
    }

    public static HttpServletRequest getRequest() {
        try {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            return request;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getTaskId() {
        return getRequest().getHeader("downloadTaskId");
    }
    public static String getUserId() {
        return getRequest().getHeader("userId");
    }

    /**
     * 获取请求体参数，主要针对post请求,兼容JSON/form格式
     *
     * @return: 参数体
     * @author: th_legend
     * @date: 2021/7/16
     **/
    public static Object handlerBodyData() {
        HttpServletRequest request = getRequest();
        if (HttpMethod.GET.name().equalsIgnoreCase(request.getMethod())) {
            return null;
        }
        String contentType = request.getHeader(CONTENT_TYPE);
        // json格式处理
        if (CONTENT_TYPE_JSON.equalsIgnoreCase(contentType)) {
            StringBuffer sb = new StringBuffer();
            InputStream is = null;
            BufferedReader br = null;
            try {
                is = request.getInputStream();
                br = new BufferedReader(new InputStreamReader(is, "utf-8"));
                String s = "";
                while ((s = br.readLine()) != null) {
                    sb.append(s);
                }
            } catch (IOException e) {
                sb = new StringBuffer();
                e.printStackTrace();
            } finally {
                try {
                    is.close();
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (sb.toString().length() <= 0) {
                return null;
            } else {
                return JSONUtil.parseObj(sb.toString());
            }
        } else {
            // form表单型
            Enumeration<String> parameterNames = request.getParameterNames();
            MultiValueMap<String, Object> map = new LinkedMultiValueMap<>(8);
            if (parameterNames != null) {
                while (parameterNames.hasMoreElements()) {
                    String element = parameterNames.nextElement();
                    map.add(element, request.getParameter(element));
                }
            }
            return map;
        }
    }


    /**
     * 请求头转发
     *
     * @return: org.springframework.http.HttpHeaders
     * @author: th_legend
     * @date: 2021/7/16
     **/
    public static HttpHeaders transferHttpHeaders() {
        HttpServletRequest request = getRequest();
        Enumeration<String> headerNames = request.getHeaderNames();
        HttpHeaders httpHeaders = new HttpHeaders();
        if (headerNames != null) {
            while (headerNames.hasMoreElements()) {
                String headName = headerNames.nextElement();
                if (EXCLUDE_HEADERS.contains(headName)) {
                    continue;
                }
                httpHeaders.add(headName, request.getHeader(headName));
            }
        }
        return httpHeaders;
    }

    public static String callback(String taskId, HttpHeaders httpHeaders) {
        return GetBeanUtil.getBean(DownloadExecutor.class).callback(taskId, httpHeaders);
    }

    public static String postResult(CallbackParam callbackParam, HttpHeaders headers) {
        return GetBeanUtil.getBean(DownloadExecutor.class).postResult(callbackParam, headers);
    }

    public static Map<String, DownloadService> getDownloadServices() {
        return downloadServices;
    }

    public static String registerService(RegisterServiceParam serviceParam) {
        return GetBeanUtil.getBean(DownloadExecutor.class).registerService(serviceParam);
    }
}
