package com.authine.cloudpivot.web.api.bean;

import lombok.Data;

/**
 * @Author: wangyong
 * @Date: 2020-01-10 10:30
 * @Description: 管控组负责人年度考核汇总表明细
 */
@Data
public class ControlGroupDetail {

    private String id;
    private String parentId;
    private Double sortKey;
    private String assessee;
    private Double quantitativeScore;
    private Double content1Score;
    private Double content2Score;
    private Double content3Score;
    private Double content4Score;
    private Double qualitativeScore;
    private Double finalScore;

}
