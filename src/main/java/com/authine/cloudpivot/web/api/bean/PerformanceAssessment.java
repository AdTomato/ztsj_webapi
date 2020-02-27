package com.authine.cloudpivot.web.api.bean;

import lombok.Data;

import java.util.Date;

/**
 * @Author: wangyong
 * @Date: 2020-02-26 00:39
 * @Description: 履职考核表
 */
@Data
public class PerformanceAssessment extends BaseBean{

    private String unit;
    private String userName;
    private String position;
    private String assessmentContent;
    private Double totalScore;
    private String result;
    private String annual;
    private Date date;
    private String judges;
    private String spaAssessmentPeopleId;

}
