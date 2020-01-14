package com.authine.cloudpivot.web.api.bean;

import lombok.Data;

/**
 * @Author:lfh
 * @Date: 2020/1/1 11:17
 * @Description：班子考核表 领导班子民主测评表
 */
@Data
public class MTALeaderBodyAppraisal {

    /**
     * id值
     */
    private String id;

    /**
     * 父id
     */
    private String parentId;

    /**
     * 测评项目
     */
    private String assessmentProject;

    /**
     * 评价意见
     */
    private String evaluationOpinions;
    /**
     * 排序字段
     */
    private Double sortKey;

    /**
     * 测评单位
     */
    private String pId;
}
