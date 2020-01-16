package com.authine.cloudpivot.web.api.bean;

import lombok.Data;

/**
 * @Author:lfh
 * @Date: 2020/1/13 11:37
 * @Description： 专家申报意见表 局（单位）聘任意见
 */
@Data
public class ReviewAppointmentComments {

    /**
     * 专家id
     */
    private String eId;
    /**
     * 姓名
     */
    private String userName;

    /**
     * 申报专业
     */
    private String expertsDeclareMajor;

    /**
     * 表决结果
     */
    private String declareOpinion;

    /**
     * 局（单位）聘任意见 id
     */
    private String id;

    /**
     * 子表业务对象父类ID
     */
    private String parentId;

    /**
     * 排序字段
     */
    private String sortKey;
}
