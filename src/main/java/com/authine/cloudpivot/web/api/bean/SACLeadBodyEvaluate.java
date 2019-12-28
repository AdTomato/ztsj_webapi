package com.authine.cloudpivot.web.api.bean;

import lombok.Data;

/**
 * @Author: wangyong
 * @Date: 2019-12-27 15:08
 * @Description: 发起职代会测评里面的“四好”领导班子评价表
 */

@Data
public class SACLeadBodyEvaluate {

    /**
     * id
     */
    private String id;

    /**
     * 父id
     */
    private String parentId;

    /**
     * 考评要点
     */
    private String reviewKeyPoints;

    /**
     * 考评内容
     */
    private String reviewKeyContent;

    /**
     * 评价分值
     */
    private Double evaluationStores;

    /**
     * 排序字段
     */
    private Double sortKey;

}
