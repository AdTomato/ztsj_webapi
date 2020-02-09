package com.authine.cloudpivot.web.api.bean;

import lombok.Data;

/**
 * @Author: wangyong
 * @Date: 2020-01-09 15:35
 * @Description: 定量考核明细表
 */
@Data
public class QuantitativeAssessDetail extends BaseBean {

    private String quantitativeAssess;
    private String assessmentContent;
    private String sheetId;
    private String deductionReason;
    private Double deduction;

}
