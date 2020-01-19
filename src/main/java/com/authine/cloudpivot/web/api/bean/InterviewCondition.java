package com.authine.cloudpivot.web.api.bean;

import io.swagger.models.auth.In;
import lombok.Data;

/**
 * @Author: wangyong
 * @Date: 2020-01-17 17:14
 * @Description:紧缺人才引进评价表详情
 */
@Data
public class InterviewCondition {

    private String id;
    private String parentId;
    private Double sortKey;
    private String gradeA;
    private String evaluationCriterion;
    private Integer fullMark;
    private String uId;
    private Double score;

}
