package com.authine.cloudpivot.web.api.bean;

import lombok.Data;

/**
 * @Author: wangyong
 * @Date: 2020-02-26 00:43
 * @Description: 履职考核表中的
 */
@Data
public class PaContent {

    private String id;
    private String parentId;
    private Double sortKey;
    private String surveyContent;
    private String evaluationScore;
    private Double finalScore;

}
