package com.authine.cloudpivot.web.api.bean;

import lombok.Data;

/**
 * @Author: wangyong
 * @Date: 2020-01-18 00:03
 * @Description:
 */
@Data
public class IntroduceEvaluationSum extends BaseBean {

    private String userName;
    private Integer age;
    private String nativePlace;
    private String nowUnitAndDuty;
    private String recommendCompany;
    private String evaluationCriterion;
    private Integer fullMark;
    private Double score;
    private Double totalPoints;
    private Integer excellentPoint;
    private Integer preferablyPoint;
    private Integer ordinaryPoint;
    private Integer poolPoint;
    private Integer hirePoint;
    private Integer notAccepted;
    private Integer positiveHeight;
    private Integer advanced;
    private Integer intermediate;
    private Integer assistantManager;
    private String expectedPosition;
    private Double expectedSalary;

}
