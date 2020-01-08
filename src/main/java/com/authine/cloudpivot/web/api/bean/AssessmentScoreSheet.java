package com.authine.cloudpivot.web.api.bean;

import lombok.Data;

/**
 * @Author: wangyong
 * @Date: 2020-01-08 16:22
 * @Description: 考核评分表
 */
@Data
public class AssessmentScoreSheet {

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
     * 评分标准
     */
    private String scoringStandard;

    /**
     * 扣分原因
     */
    private String deductionReason;

    /**
     * 扣分
     */
    private Double deduction;

}
