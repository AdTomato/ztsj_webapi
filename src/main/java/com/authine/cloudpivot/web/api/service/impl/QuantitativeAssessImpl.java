package com.authine.cloudpivot.web.api.service.impl;

import com.authine.cloudpivot.web.api.bean.*;
import com.authine.cloudpivot.web.api.mapper.QuantitativeAssessMapper;
import com.authine.cloudpivot.web.api.service.IQuantitativeAssessService;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: wangyong
 * @Date: 2020-01-08 11:18
 * @Description: 管控组负责人年度定量考核Service
 */
public class QuantitativeAssessImpl implements IQuantitativeAssessService {

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
    public QuantitativeAssess getQuantitativeAssessById(String id) {
        return quantitativeAssessMapper.getQuantitativeAssessById(id);
    }

    @Override
    public void updateQuantitativeAssessById(QuantitativeAssess quantitativeAssess) {
        quantitativeAssessMapper.updateQuantitativeAssessById(quantitativeAssess);
    }

    @Override
    public void insertQuantitativeSafeManage(List<AssessmentScoreSheet> assessmentScoreSheets) {
        quantitativeAssessMapper.insertQuantitativeSafeManage(assessmentScoreSheets);
    }

    @Override
    public List<AssessmentScoreSheet> getQuantitativeSafeManageByParentId(String id) {
        return quantitativeAssessMapper.getQuantitativeSafeManageByParentId(id);
    }

    @Override
    public void updateQuantitativeSafeManageById(List<AssessmentScoreSheet> assessmentScoreSheetList) {
        quantitativeAssessMapper.updateQuantitativeSafeManageById(assessmentScoreSheetList);
    }

    @Override
    public void insertQuantitativeQualityManage(List<AssessmentScoreSheet> assessmentScoreSheets) {
        quantitativeAssessMapper.insertQuantitativeQualityManage(assessmentScoreSheets);
    }

    @Override
    public List<AssessmentScoreSheet> getQuantitativeQualityManageByParentId(String id) {
        return quantitativeAssessMapper.getQuantitativeQualityManageByParentId(id);
    }

    @Override
    public void updateQuantitativeQualityManageById(List<AssessmentScoreSheet> assessmentScoreSheetList) {
        quantitativeAssessMapper.updateQuantitativeQualityManageById(assessmentScoreSheetList);
    }

    @Override
    public void insertQuantitativeScManage(List<AssessmentScoreSheet> assessmentScoreSheets) {
        quantitativeAssessMapper.insertQuantitativeScManage(assessmentScoreSheets);
    }

    @Override
    public List<AssessmentScoreSheet> getQuantitativeScManageByParentId(String id) {
        return quantitativeAssessMapper.getQuantitativeScManageByParentId(id);
    }

    @Override
    public void updateQuantitativeScManageById(List<AssessmentScoreSheet> assessmentScoreSheetList) {
        quantitativeAssessMapper.updateQuantitativeScManageById(assessmentScoreSheetList);
    }

    @Override
    public void insertQuantitativeSkillManage(List<AssessmentScoreSheet> assessmentScoreSheets) {
        quantitativeAssessMapper.insertQuantitativeSkillManage(assessmentScoreSheets);
    }

    @Override
    public List<AssessmentScoreSheet> getQuantitativeSkillManageByParentId(String id) {
        return quantitativeAssessMapper.getQuantitativeSkillManageByParentId(id);
    }

    @Override
    public void updateQuantitativeSkillManageById(List<AssessmentScoreSheet> assessmentScoreSheetList) {
        quantitativeAssessMapper.updateQuantitativeSkillManageById(assessmentScoreSheetList);
    }

    @Override
    public void insertQuantitativeEngineManag(List<AssessmentScoreSheet> assessmentScoreSheets) {
        quantitativeAssessMapper.insertQuantitativeEngineManag(assessmentScoreSheets);
    }

    @Override
    public List<AssessmentScoreSheet> getQuantitativeEngineManagByParentId(String id) {
        return quantitativeAssessMapper.getQuantitativeEngineManagByParentId(id);
    }

    @Override
    public void updateQuantitativeEngineManagById(List<AssessmentScoreSheet> assessmentScoreSheetList) {
        quantitativeAssessMapper.updateQuantitativeEngineManagById(assessmentScoreSheetList);
    }

    @Override
    public void insertQuantitativeCreditEvaluat(List<AssessmentScoreSheet> assessmentScoreSheets) {
        quantitativeAssessMapper.insertQuantitativeCreditEvaluat(assessmentScoreSheets);
    }

    @Override
    public List<AssessmentScoreSheet> getQuantitativeCreditEvaluatByParentId(String id) {
        return quantitativeAssessMapper.getQuantitativeCreditEvaluatByParentId(id);
    }

    @Override
    public void updateQuantitativeCreditEvaluatById(List<AssessmentScoreSheet> assessmentScoreSheetList) {
        quantitativeAssessMapper.updateQuantitativeCreditEvaluatById(assessmentScoreSheetList);
    }

    @Override
    public void insertQuantitativeProblem(List<AssessmentScoreSheet> assessmentScoreSheets) {
        quantitativeAssessMapper.insertQuantitativeProblem(assessmentScoreSheets);
    }

    @Override
    public List<AssessmentScoreSheet> getQuantitativeProblemByParentId(String id) {
        return quantitativeAssessMapper.getQuantitativeProblemByParentId(id);
    }

    @Override
    public void updateQuantitativeProblemById(List<AssessmentScoreSheet> assessmentScoreSheetList) {
        quantitativeAssessMapper.updateQuantitativeProblemById(assessmentScoreSheetList);
    }

    @Override
    public void insertQuantitativeControl(List<AssessmentScoreSheet> assessmentScoreSheets) {
        quantitativeAssessMapper.insertQuantitativeControl(assessmentScoreSheets);
    }

    @Override
    public List<AssessmentScoreSheet> getQuantitativeControlByParentId(String id) {
        return quantitativeAssessMapper.getQuantitativeControlByParentId(id);
    }

    @Override
    public void updateQuantitativeControlById(List<AssessmentScoreSheet> assessmentScoreSheetList) {
        quantitativeAssessMapper.updateQuantitativeControlById(assessmentScoreSheetList);
    }

    @Override
    public void insertQuantitativeService(List<AssessmentScoreSheet> assessmentScoreSheets) {
        quantitativeAssessMapper.insertQuantitativeService(assessmentScoreSheets);
    }

    @Override
    public List<AssessmentScoreSheet> getQuantitativeServiceByParentId(String id) {
        return quantitativeAssessMapper.getQuantitativeServiceByParentId(id);
    }

    @Override
    public void updateQuantitativeServiceById(List<AssessmentScoreSheet> assessmentScoreSheetList) {
        quantitativeAssessMapper.updateQuantitativeServiceById(assessmentScoreSheetList);
    }

    @Override
    public void insertQuantitativeDiscipline(List<AssessmentScoreSheet> assessmentScoreSheets) {
        quantitativeAssessMapper.insertQuantitativeDiscipline(assessmentScoreSheets);
    }

    @Override
    public List<AssessmentScoreSheet> getQuantitativeDisciplineByParentId(String id) {
        return quantitativeAssessMapper.getQuantitativeDisciplineByParentId(id);
    }

    @Override
    public void updateQuantitativeDisciplineById(List<AssessmentScoreSheet> assessmentScoreSheetList) {
        quantitativeAssessMapper.updateQuantitativeDisciplineById(assessmentScoreSheetList);
    }

    @Override
    public List<QuantitativeAssessDetail> getQuantitativeAssessDetailById(String id) {
        return quantitativeAssessMapper.getQuantitativeAssessDetailById(id);
    }

    @Override
    public String getQuantitativeAssessStatus(String id) {
        return quantitativeAssessMapper.getQuantitativeAssessStatus(id);
    }
}
