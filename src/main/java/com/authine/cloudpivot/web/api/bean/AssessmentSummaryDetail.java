package com.authine.cloudpivot.web.api.bean;

import lombok.Data;

/**
 * @Author: wangyong
 * @Date: 2019/12/15
 * @Description: 局机关部门年度考核得分汇总表明细
 */
@Data
public class AssessmentSummaryDetail {

    private String id;
    private String parentId;
    private String department;
    private Double firstQuarter;
    private Double secondQuarter;
    private Double thirdQuarter;
    private Double fourQuarter;
    private Double annualEvaluation;
    private Double annualScore;

}
