package com.authine.cloudpivot.web.api.bean;

import lombok.Data;

/**
 * @Author: wangyong
 * @Date: 2019-12-28 16:59
 * @Description: 职代会测评党风廉政建设情况测评表
 */
@Data
public class ACHonestEvaluationForm {

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
     * 职代会测评id
     */
    private String pId;

    /**
     * 评议内容
     */
    private String reviewContent;

    /**
     * 评议意见
     */
    private String reviewOpinion;

}
