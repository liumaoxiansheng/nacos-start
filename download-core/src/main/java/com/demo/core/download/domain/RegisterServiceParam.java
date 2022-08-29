package com.demo.core.download.domain;

import lombok.Data;

/**
 * @className: RegisterServiceParam
 * @description: TODO 类描述
 * @author: th_legend
 * @date: 2021/7/19
 **/
@Data
public class RegisterServiceParam {
    /**
     * 服务名称，必须保证唯一
     **/
    private String name;

    /**
     * url，必须保证正确的url(可访问):contextPath+controllerMapping+methodMapping
     **/
    private String requestUrl;

    /**
     * 请求方法类型，预留，暂时不用，目前支持GET和POST
     **/
    private String requestMethod;

    /**
     * 当前微服务名称
     **/
    private String applicationName;

    /**
     * 服务描述
     **/
    private String description;
}
