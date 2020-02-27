package com.authine.cloudpivot.web.api.bean;

import lombok.Data;

/**
 * @Author: wangyong
 * @Date: 2020-02-26 09:44
 * @Description:
 */
@Data
public class PerformanceAssessmentDet extends BaseBean {

    private String performanceAssessment;
    private String paContentId;
    private String surveyContent;
    private Double evaluationScore;

}
