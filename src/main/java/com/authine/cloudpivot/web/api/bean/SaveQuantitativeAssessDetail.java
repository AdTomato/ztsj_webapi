package com.authine.cloudpivot.web.api.bean;

import lombok.Data;

import java.util.List;

/**
 * @Author: wangyong
 * @Date: 2020-01-08 23:03
 * @Description: 管控组定量考核每个人的打分情况
 */
@Data
public class SaveQuantitativeAssessDetail {

    private String id;

    /**
     * 安全管理
     */
    private List<AssessmentScoreSheet> quantitativeSafeManage;

    /**
     * 质量管理
     */
    private List<AssessmentScoreSheet> quantitativeQualityManage;

    /**
     * 进度及施工组织管理
     */
    private List<AssessmentScoreSheet> quantitativeScManage;

    /**
     * 技术管理
     */
    private List<AssessmentScoreSheet> quantitativeSkillManage;


    /**
     * 工程分包管理
     */
    private List<AssessmentScoreSheet> quantitativeEngineManag;

    /**
     * 铁路、公路信用评价
     */
    private List<AssessmentScoreSheet> quantitativeCreditEvaluat;

    /**
     * 沟通协调、重大问题报告
     */
    private List<AssessmentScoreSheet> quantitativeProblem;

    /**
     * 管控工作规范性
     */
    private List<AssessmentScoreSheet> quantitativeControl;

    /**
     * 对片区内在建项目的服务、指导
     */
    private List<AssessmentScoreSheet> quantitativeService;

    /**
     * 稽查纪律
     */
    private List<AssessmentScoreSheet> quantitativeDiscipline;

}
