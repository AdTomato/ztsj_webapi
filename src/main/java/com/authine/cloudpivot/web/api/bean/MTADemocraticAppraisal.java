package com.authine.cloudpivot.web.api.bean;

import lombok.Data;

/**
 * @Author:lfh
 * @Date: 2020/1/1 11:25
 * @Description：班子考核表 领导人员及非领导职务人员民主测评表
 */
@Data
public class MTADemocraticAppraisal {

    /**
     * 民主测评表id
     */
    private String id;

    /**
     * 民主测评表父id
     */
    private String parentId;

    /**
     * 领导人员
     */
    private String leadershipName;

    /**
     * 领导人员职务
     */
    private String leadershipDuty;

    /**
     * 选项
     */
    private String option;
    /**
     * 排序字段
     */
    private String sortKey;

    /**
     * 测评单位
     */
    private String pId;
}
