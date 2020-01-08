package com.authine.cloudpivot.web.api.service;

import com.authine.cloudpivot.web.api.bean.AssessmentScoreSheet;
import com.authine.cloudpivot.web.api.bean.QaJudges;

import java.util.List;

/**
 * @Author: wangyong
 * @Date: 2020-01-08 11:17
 * @Description: 管控组负责人年度定量考核Service接口
 */
public interface IQuantitativeAssessService {

    public void insertQaJudges(List<QaJudges> qaJudgesList);

    void insertQuantitativeSafeManage(List<AssessmentScoreSheet> assessmentScoreSheets);

    void insertQuantitativeQualityManage(List<AssessmentScoreSheet> assessmentScoreSheets);

    void insertQuantitativeScManage(List<AssessmentScoreSheet> assessmentScoreSheets);

    void insertQuantitativeSkillManage(List<AssessmentScoreSheet> assessmentScoreSheets);

    void insertQuantitativeEngineManag(List<AssessmentScoreSheet> assessmentScoreSheets);

    void insertQuantitativeCreditEvaluat(List<AssessmentScoreSheet> assessmentScoreSheets);

    void insertQuantitativeProblem(List<AssessmentScoreSheet> assessmentScoreSheets);

    void insertQuantitativeControl(List<AssessmentScoreSheet> assessmentScoreSheets);

    void insertQuantitativeService(List<AssessmentScoreSheet> assessmentScoreSheets);

    void insertQuantitativeDiscipline(List<AssessmentScoreSheet> assessmentScoreSheets);

}
