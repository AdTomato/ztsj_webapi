package com.authine.cloudpivot.web.api.bean;

import lombok.Data;

/**
 * @Author: wangyong
 * @Date: 2020-01-17 23:55
 * @Description:
 */
@Data
public class IntroduceEvaluation extends BaseBean {

    private String userName;
    private Integer age;
    private String nativePlace;
    private String nowUnitAndDuty;
    private String recommendCompany;
    private String judges;
    private String weatherExpert;
    private String evaluationCriterion;
    private Integer fullMark;
    private Double score;
    private String evaluationResult;
    private String officeAdvice;
    private String rating;
    private String expectedPosition;
    private Double expectedSalary;

}
