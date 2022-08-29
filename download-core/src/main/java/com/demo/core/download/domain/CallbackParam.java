package com.demo.core.download.domain;

import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * @className: CallbackParam
 * @description: 回调参数体
 * @author: th_legend
 * @date: 2021/7/15
 **/
@Data
@ToString
public class CallbackParam {

    /**
     * 执行的任务id
     **/
    private String taskId;

    /**
     * 状态 0 失败 1 进行中 2 已完成
     **/
    private int status ;
    /**
     * 总数
     *
     **/
    private int totalCount ;

    /**
     * 完成数
     *
     **/
    private int completeCount ;

    /**
     * 模板完成后的下载地址
     *
     **/
    private List<String> downLoadUrls ;

}
