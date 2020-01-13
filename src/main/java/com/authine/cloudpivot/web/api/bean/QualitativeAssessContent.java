package com.authine.cloudpivot.web.api.bean;

import lombok.Data;

/**
 * @Author: wangyong
 * @Date: 2020-01-09 23:08
 * @Description: 定性考核内容明细
 */
@Data
public class QualitativeAssessContent {

    private String id;
    private String parentId;
    private Double sortKey;
    private String evaluationContent;
    private Integer evaluationScore;
    private String evaluationKps;
    private Integer score;
    private Double finalScore;

}
