package com.authine.cloudpivot.web.api.bean;

import lombok.Data;

/**
 * @Author: wangyong
 * @Date: 2020-01-10 00:00
 * @Description: 定性考核明细
 */
@Data
public class QualitativeAssessDetail {

    private String id;
    private String parentId;
    private String qualitativeAssess;
    private String evaluationContent;
    private String sheetId;
    private Integer score;

}
