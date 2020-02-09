package com.authine.cloudpivot.web.api.bean;

import lombok.Data;

/**
 * 机关部门考核明细
 */
@Data
public class AssessmentDetail {

    private String deartment_assessment;
    private String assessment_project;
    private Double score;

}
