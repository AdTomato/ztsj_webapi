package com.authine.cloudpivot.web.api.mapper;

import com.authine.cloudpivot.web.api.bean.*;

import java.util.List;

/**
 * @Author: wangyong
 * @Date: 2020-01-08 11:16
 * @Description: 管控组负责人年度定量考核Mapper
 */
public interface QuantitativeAssessMapper {


    /**
     * 创建定量考核里面的存储评委的子表
     * @param qaJudgesList 评委列表
     */
    void insertQaJudges(List<QaJudges> qaJudgesList);

    void insertQuantitativeDiscipline(List<AssessmentScoreSheet> assessmentScoreSheets);

    void insertQuantitativeService(List<AssessmentScoreSheet> assessmentScoreSheets);

    void insertQuantitativeControl(List<AssessmentScoreSheet> assessmentScoreSheets);

    void insertQuantitativeProblem(List<AssessmentScoreSheet> assessmentScoreSheets);

    void insertQuantitativeCreditEvaluat(List<AssessmentScoreSheet> assessmentScoreSheets);

    void insertQuantitativeEngineManag(List<AssessmentScoreSheet> assessmentScoreSheets);

    void insertQuantitativeSkillManage(List<AssessmentScoreSheet> assessmentScoreSheets);

    void insertQuantitativeScManage(List<AssessmentScoreSheet> assessmentScoreSheets);

    void insertQuantitativeQualityManage(List<AssessmentScoreSheet> assessmentScoreSheets);

    void insertQuantitativeSafeManage(List<AssessmentScoreSheet> assessmentScoreSheets);

    QuantitativeAssess getQuantitativeAssessById(String id);

    void updateQuantitativeAssessById(QuantitativeAssess quantitativeAssess);

    void updateQuantitativeSafeManageById(List<AssessmentScoreSheet> assessmentScoreSheetList);

    List<AssessmentScoreSheet> getQuantitativeSafeManageByParentId(String id);

    List<AssessmentScoreSheet> getQuantitativeQualityManageByParentId(String id);

    void updateQuantitativeQualityManageById(List<AssessmentScoreSheet> assessmentScoreSheetList);

    List<AssessmentScoreSheet> getQuantitativeScManageByParentId(String id);

    void updateQuantitativeScManageById(List<AssessmentScoreSheet> assessmentScoreSheetList);

    List<AssessmentScoreSheet> getQuantitativeSkillManageByParentId(String id);

    void updateQuantitativeSkillManageById(List<AssessmentScoreSheet> assessmentScoreSheetList);

    List<AssessmentScoreSheet> getQuantitativeEngineManagByParentId(String id);

    void updateQuantitativeEngineManagById(List<AssessmentScoreSheet> assessmentScoreSheetList);

    List<AssessmentScoreSheet> getQuantitativeCreditEvaluatByParentId(String id);

    void updateQuantitativeCreditEvaluatById(List<AssessmentScoreSheet> assessmentScoreSheetList);

    List<AssessmentScoreSheet> getQuantitativeProblemByParentId(String id);

    void updateQuantitativeProblemById(List<AssessmentScoreSheet> assessmentScoreSheetList);

    List<AssessmentScoreSheet> getQuantitativeControlByParentId(String id);

    void updateQuantitativeControlById(List<AssessmentScoreSheet> assessmentScoreSheetList);

    List<AssessmentScoreSheet> getQuantitativeServiceByParentId(String id);

    void updateQuantitativeServiceById(List<AssessmentScoreSheet> assessmentScoreSheetList);

    List<AssessmentScoreSheet> getQuantitativeDisciplineByParentId(String id);

    void updateQuantitativeDisciplineById(List<AssessmentScoreSheet> assessmentScoreSheetList);

    List<QuantitativeAssessDetail> getQuantitativeAssessDetailById(String id);

    String getQuantitativeAssessStatus(String id);
}
