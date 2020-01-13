package com.authine.cloudpivot.web.api.service;

import com.authine.cloudpivot.web.api.bean.*;

import java.util.List;

/**
 * @Author: wangyong
 * @Date: 2020-01-08 11:17
 * @Description: 管控组负责人年度定量考核Service接口
 */
public interface IQuantitativeAssessService {

    void insertQaJudges(List<QaJudges> qaJudgesList);
    QuantitativeAssess getQuantitativeAssessById(String id);
    void updateQuantitativeAssessById(QuantitativeAssess quantitativeAssess);

    void insertQuantitativeSafeManage(List<AssessmentScoreSheet> assessmentScoreSheets);
    List<AssessmentScoreSheet> getQuantitativeSafeManageByParentId(String id);
    void updateQuantitativeSafeManageById(List<AssessmentScoreSheet> assessmentScoreSheetList);

    void insertQuantitativeQualityManage(List<AssessmentScoreSheet> assessmentScoreSheets);
    List<AssessmentScoreSheet> getQuantitativeQualityManageByParentId(String id);
    void updateQuantitativeQualityManageById(List<AssessmentScoreSheet> assessmentScoreSheetList);

    void insertQuantitativeScManage(List<AssessmentScoreSheet> assessmentScoreSheets);
    List<AssessmentScoreSheet> getQuantitativeScManageByParentId(String id);
    void updateQuantitativeScManageById(List<AssessmentScoreSheet> assessmentScoreSheetList);

    void insertQuantitativeSkillManage(List<AssessmentScoreSheet> assessmentScoreSheets);
    List<AssessmentScoreSheet> getQuantitativeSkillManageByParentId(String id);
    void updateQuantitativeSkillManageById(List<AssessmentScoreSheet> assessmentScoreSheetList);

    void insertQuantitativeEngineManag(List<AssessmentScoreSheet> assessmentScoreSheets);
    List<AssessmentScoreSheet> getQuantitativeEngineManagByParentId(String id);
    void updateQuantitativeEngineManagById(List<AssessmentScoreSheet> assessmentScoreSheetList);

    void insertQuantitativeCreditEvaluat(List<AssessmentScoreSheet> assessmentScoreSheets);
    List<AssessmentScoreSheet> getQuantitativeCreditEvaluatByParentId(String id);
    void updateQuantitativeCreditEvaluatById(List<AssessmentScoreSheet> assessmentScoreSheetList);

    void insertQuantitativeProblem(List<AssessmentScoreSheet> assessmentScoreSheets);
    List<AssessmentScoreSheet> getQuantitativeProblemByParentId(String id);
    void updateQuantitativeProblemById(List<AssessmentScoreSheet> assessmentScoreSheetList);

    void insertQuantitativeControl(List<AssessmentScoreSheet> assessmentScoreSheets);
    List<AssessmentScoreSheet> getQuantitativeControlByParentId(String id);
    void updateQuantitativeControlById(List<AssessmentScoreSheet> assessmentScoreSheetList);

    void insertQuantitativeService(List<AssessmentScoreSheet> assessmentScoreSheets);
    List<AssessmentScoreSheet> getQuantitativeServiceByParentId(String id);
    void updateQuantitativeServiceById(List<AssessmentScoreSheet> assessmentScoreSheetList);

    void insertQuantitativeDiscipline(List<AssessmentScoreSheet> assessmentScoreSheets);
    List<AssessmentScoreSheet> getQuantitativeDisciplineByParentId(String id);
    void updateQuantitativeDisciplineById(List<AssessmentScoreSheet> assessmentScoreSheetList);

    List<QuantitativeAssessDetail> getQuantitativeAssessDetailById(String id);

    String getQuantitativeAssessStatus(String id);

}
