package com.study.config;

import com.alibaba.csp.sentinel.slots.block.BlockException;

/**
 * @className: MyBlokHandler
 * @description: TODO 类描述
 * @author: th_legend
 * @date: 2021/7/12
 **/
public class MyBlockHandler {
    public static String exceptionHandler1(BlockException e){

        return "exceptionHandler1()....";
    }
}
