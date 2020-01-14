package com.authine.cloudpivot.web.api.bean;

import lombok.Data;

/**
 * @Author:lfh
 * @Date: 2020/1/1 10:05
 * @Description：发起班子考核测评领导班子民主测评表
 */
@Data
public class SMTALeaderBodyAppraisal {
    /**
     * id值
     */
    private String id;

    /**
     * 父id
     */
    private String parentId;

    /**
     * 排序字段
     */
    private Double sortKey;

    /**
     * 测评项目
     */
    private String assessmentProject;

    /**
     * 好票数
     */
    private Integer goodPoll;

    /**
     * 较好票数
     */
    private Integer preferablyPoll;

    /**
     * 一般票数
     */
    private Integer ordinaryPoll;

    /**
     * 差票数
     */
    private Integer badPoll;
}
