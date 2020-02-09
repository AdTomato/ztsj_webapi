package com.authine.cloudpivot.web.api.bean;

import lombok.Data;

/**
 * @Author:lfh
 * @Date: 2020/1/13 11:28
 * @Description： 专家申报 基层单位推荐意见 参评具备条件表
 */
@Data
public class ConditionsParticipations {
    /**
     * 具体项目及完成效果
     */
    private String projectsAndEffect;
    /**
     * 审查意见
     */
    private String reviewOpinions;

    /**
     * 基层单位或部门推荐意见
     */
    private String unitsOrDeptOpinion;

    /**
     * 参评具备条件表id
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
