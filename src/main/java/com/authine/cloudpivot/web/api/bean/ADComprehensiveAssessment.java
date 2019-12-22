package com.authine.cloudpivot.web.api.bean;

import lombok.Data;

/**
 * 局机关部门综合评价表
 */
@Data
public class ADComprehensiveAssessment {

    private String id;
    private String parentId;
    private String assessmentProject;
    private Integer resultScore;

}
