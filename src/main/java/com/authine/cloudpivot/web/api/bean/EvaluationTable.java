package com.authine.cloudpivot.web.api.bean;

import lombok.Data;

/**
 * 机关部门考核打分明细
 */
@Data
public class EvaluationTable {

    /**
     * id值
     */
    private String id;

    /**
     * 父id
     */
    private String parentId;

    /**
     * 测评项目
     */
    private String assessmentProject;

    /**
     * 测评内容
     */
    private String assessmentContent;

    /**
     * 评分标准
     */
    private String scaleOfMark;


}
