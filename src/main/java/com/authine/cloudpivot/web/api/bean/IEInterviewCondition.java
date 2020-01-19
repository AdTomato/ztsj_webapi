package com.authine.cloudpivot.web.api.bean;

import lombok.Data;

/**
 * @Author: wangyong
 * @Date: 2020-01-18 00:13
 * @Description:
 */
@Data
public class IEInterviewCondition {

    private String id;
    private String parentId;
    private Double sortKey;
    private String gradeA;
    private String evaluationCriterion;
    private String fullMark;
    private String score;

}
