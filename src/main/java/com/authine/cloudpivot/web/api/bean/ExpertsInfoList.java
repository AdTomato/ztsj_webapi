package com.authine.cloudpivot.web.api.bean;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @Author:lfh
 * @Date: 2020/1/19 14:12
 * @Description：专家参评人选基本情况一览表
 */
@Data
public class ExpertsInfoList {
    /**
     * id
     */
    private String id;

    /**
     * 姓名
     */
    private String userName;

    /**
     * 单位
     */
    private String unit;

    /**
     * 性别
     */
    private String gender;

    /**
     * 出生年月
     */
    private Date birth;

    /**
     * 职务
     */
    private String post;

    /**
     * 职称
     */
    private String positionalTitles;

    /**
     * 第一学历
     */
    private String firstEducation;

    /**
     * 第一学历毕业院校及专业
     */
    private String graduatesAndMajors;

    /**
     * 最高学历学位
     */
    private String TheHighestEducationDegree;
    /**
     * 最高学历毕业院校和专业
     */
    private String highestGraduatesAndMajors;

    /**
     * 先从事专业
     */
    private String nowMajorIn;

    /**
     * 参评条件
     */
    private ConditionsParticipations conditionsParticipations;

    /**
     * 主要工作业绩
     */
    private String mainAchievements;

    /**
     * 申报届别 系别  级别
     */
    private String declareSessionDeptGrade;

    /**
     * 备注
     */
    private String remarks;
}
