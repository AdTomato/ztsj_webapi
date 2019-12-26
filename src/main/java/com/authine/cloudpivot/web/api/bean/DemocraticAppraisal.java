package com.authine.cloudpivot.web.api.bean;

import lombok.Data;

/**
 * @Author: wangyong
 * @Date: 2019-12-24 16:26
 * @Description: 职代会测评表中的民主测评表
 */
@Data
public class DemocraticAppraisal {

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
