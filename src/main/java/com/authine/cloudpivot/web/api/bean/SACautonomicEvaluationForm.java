package com.authine.cloudpivot.web.api.bean;

import lombok.Data;

/**
 * @Author: wangyong
 * @Date: 2019-12-30 10:28
 * @Description: 发起职代会测评中铁四局领导人员落实党风廉政建设责任制和廉洁自律情况测评表
 */
@Data
public class SACautonomicEvaluationForm {

    /**
     * id值
     */
    private String id;

    /**
     * 父id
     */
    private String parentId;

    /**
     * 排序等级
     */
    private Double sortKey;

    /**
     * 领导人员
     */
    private String leadershipName;

    /**
     * 职务
     */
    private String leadershipDuty;

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

    /**
     * 弃权票数
     */
    private Integer waiverPoll;

}
