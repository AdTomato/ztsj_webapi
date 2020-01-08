package com.authine.cloudpivot.web.api.service.impl;

import com.authine.cloudpivot.web.api.bean.AssessmentScoreSheet;
import com.authine.cloudpivot.web.api.bean.QaJudges;
import com.authine.cloudpivot.web.api.mapper.QuantitativeAssessMapper;
import com.authine.cloudpivot.web.api.service.IQuantitativeAssessService;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: wangyong
 * @Date: 2020-01-08 11:18
 * @Description: 管控组负责人年度定量考核Service
 */
public class QuantitativeAssessService implements IQuantitativeAssessService {

    @Resource
    QuantitativeAssessMapper quantitativeAssessMapper;

    /**
     * @Author: wangyong
     * @Date: 2020/1/8 11:41
     * @param qaJudgesList : 评委列表
     * @return : void
     * @Description: 创建定量考核里面的存储评委的子表
     */
    @Override
    public void insertQaJudges(List<QaJudges> qaJudgesList) {
        quantitativeAssessMapper.insertQaJudges(qaJudgesList);
    }

    @Override
    public void insertQuantitativeSafeManage(List<AssessmentScoreSheet> assessmentScoreSheets) {
        quantitativeAssessMapper.insertQuantitativeSafeManage(assessmentScoreSheets);
    }

    @Override
    public void insertQuantitativeQualityManage(List<AssessmentScoreSheet> assessmentScoreSheets) {
        quantitativeAssessMapper.insertQuantitativeQualityManage(assessmentScoreSheets);
    }

    @Override
    public void insertQuantitativeScManage(List<AssessmentScoreSheet> assessmentScoreSheets) {
        quantitativeAssessMapper.insertQuantitativeScManage(assessmentScoreSheets);
    }

    @Override
    public void insertQuantitativeSkillManage(List<AssessmentScoreSheet> assessmentScoreSheets) {
        quantitativeAssessMapper.insertQuantitativeSkillManage(assessmentScoreSheets);
    }

    @Override
    public void insertQuantitativeEngineManag(List<AssessmentScoreSheet> assessmentScoreSheets) {
        quantitativeAssessMapper.insertQuantitativeEngineManag(assessmentScoreSheets);
    }

    @Override
    public void insertQuantitativeCreditEvaluat(List<AssessmentScoreSheet> assessmentScoreSheets) {
        quantitativeAssessMapper.insertQuantitativeCreditEvaluat(assessmentScoreSheets);
    }

    @Override
    public void insertQuantitativeProblem(List<AssessmentScoreSheet> assessmentScoreSheets) {
        quantitativeAssessMapper.insertQuantitativeProblem(assessmentScoreSheets);
    }

    @Override
    public void insertQuantitativeControl(List<AssessmentScoreSheet> assessmentScoreSheets) {
        quantitativeAssessMapper.insertQuantitativeControl(assessmentScoreSheets);
    }

    @Override
    public void insertQuantitativeService(List<AssessmentScoreSheet> assessmentScoreSheets) {
        quantitativeAssessMapper.insertQuantitativeService(assessmentScoreSheets);
    }

    @Override
    public void insertQuantitativeDiscipline(List<AssessmentScoreSheet> assessmentScoreSheets) {
        quantitativeAssessMapper.insertQuantitativeDiscipline(assessmentScoreSheets);
    }
}
