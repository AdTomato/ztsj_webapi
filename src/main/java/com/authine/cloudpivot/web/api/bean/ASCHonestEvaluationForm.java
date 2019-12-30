package com.authine.cloudpivot.web.api.bean;

import lombok.Data;

/**
 * @Author: wangyong
 * @Date: 2019-12-28 16:55
 * @Description: 发起职代会测评领导班子党风廉政建设情况测评表
 */
@Data
public class ASCHonestEvaluationForm {

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
     * 评议内容
     */
    private String reviewContent;

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
