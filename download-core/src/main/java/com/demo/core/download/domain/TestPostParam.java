package com.demo.core.download.domain;

import lombok.Data;

/**
 * @className: TestPostParam
 * @description: TODO 类描述
 * @author: th_legend
 * @date: 2021/7/15
 **/
@Data
public class TestPostParam {

    // 执行的任务id
    private String taskId;

    // 总数
    private int totalCount ;
}
