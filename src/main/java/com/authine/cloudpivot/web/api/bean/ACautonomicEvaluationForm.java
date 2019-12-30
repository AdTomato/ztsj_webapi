package com.authine.cloudpivot.web.api.bean;

import lombok.Data;

/**
 * @Author: wangyong
 * @Date: 2019-12-30 10:33
 * @Description: 职代会测评中铁四局领导人员落实党风廉政建设责任制和廉洁自律情况测评表
 */
@Data
public class ACautonomicEvaluationForm {

    /**
     * id
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
     * 发起职代会测评id
     */
    private String pId;

    /**
     * 领导人员
     */
    private String leadershipName;

    /**
     * 职务
     */
    private String leadershipDuty;

    /**
     * 选项
     */
    private String option;

}
