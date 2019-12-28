package com.authine.cloudpivot.web.api.bean;

import lombok.Data;

/**
 * @Author: wangyong
 * @Date: 2019-12-28 15:41
 * @Description: 职代会测评领导班子民主测评表
 */
@Data
public class ACLeaderBodyAppraisal {

    /**
     * id值
     */
    private String id;

    /**
     * 父id
     */
    private String parentId;

    /**
     * 发起职代会测评id
     */
    private String pId;

    /**
     * 排序等级
     */
    private Double sortKey;

    /**
     * 测评项目
     */
    private String assessmentProject;

    /**
     * 评价意见
     */
    private String evaluationOpinions;

}
