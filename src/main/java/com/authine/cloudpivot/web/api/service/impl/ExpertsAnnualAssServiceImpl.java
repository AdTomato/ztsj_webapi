package com.authine.cloudpivot.web.api.service.impl;

import com.authine.cloudpivot.web.api.bean.ExpertAnnualAvgScore;
import com.authine.cloudpivot.web.api.bean.ExpertsAssessInfo;
import com.authine.cloudpivot.web.api.bean.ExpertsInfos;
import com.authine.cloudpivot.web.api.mapper.ExpertsAnnualAssMapper;
import com.authine.cloudpivot.web.api.service.ExpertsAnnualAssService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service
public class ExpertsAnnualAssServiceImpl implements ExpertsAnnualAssService {
    @Resource
    private ExpertsAnnualAssMapper expertsAnnualAssMapper;
    @Override
    public List<ExpertsInfos> findExportsList(ExpertsAssessInfo expertsAssessInfo) {
        return expertsAnnualAssMapper.findExportsList(expertsAssessInfo);
    }

    @Override
    public List<ExpertsInfos> findAllExportsList(ExpertsAssessInfo expertsAssessInfo) {
        return expertsAnnualAssMapper.findAllExportsList(expertsAssessInfo);

    }

    @Override
    public void resetFinancialauditannual(String parentId) {
        expertsAnnualAssMapper.resetFinancialauditannual(parentId);
    }

    @Override
    public void resetEngineerTechAnnual(String parentId) {
        expertsAnnualAssMapper.resetEngineerTechAnnual(parentId);
    }

    @Override
    public void resetEngineerEconomicAnnual(String parentId) {
        expertsAnnualAssMapper.resetEngineerEconomicAnnual(parentId);
    }

    @Override
    public List<ExpertAnnualAvgScore> countFinanceAvg(String id) {
        return expertsAnnualAssMapper.countFinanceAvg(id);
    }

    @Override
    public List<ExpertAnnualAvgScore> countTechAvg(String id) {
        return expertsAnnualAssMapper.countTechAvg(id);
    }

    @Override
    public List<ExpertAnnualAvgScore> countEconomicAvg(String id) {
        return expertsAnnualAssMapper.countEconomicAvg(id);
    }

    @Override
    public void updateFinanceAvg(List<ExpertAnnualAvgScore> expertAnnualAvgScores) {
        expertsAnnualAssMapper.updateFinanceAvg(expertAnnualAvgScores);
    }

    @Override
    public void updateFinanceDeptJudgesScore(List<ExpertAnnualAvgScore> expertAnnualAvgScores) {
        expertsAnnualAssMapper.updateFinanceDeptJudgesScore(expertAnnualAvgScores);

    }

    @Override
    public void updateFinanceBureauJudgesScore(List<ExpertAnnualAvgScore> expertAnnualAvgScores) {
        expertsAnnualAssMapper.updateFinanceBureauJudgesScore(expertAnnualAvgScores);

    }

    @Override
    public void updateTechAvg(List<ExpertAnnualAvgScore> expertAnnualAvgScores) {
        expertsAnnualAssMapper.updateTechAvg(expertAnnualAvgScores);
    }

    @Override
    public void updateTechDeptJudgesScore(List<ExpertAnnualAvgScore> expertAnnualAvgScores) {
        expertsAnnualAssMapper.updateTechDeptJudgesScore(expertAnnualAvgScores);

    }

    @Override
    public void updateTechBureauJudgesScore(List<ExpertAnnualAvgScore> expertAnnualAvgScores) {
        expertsAnnualAssMapper.updateTechBureauJudgesScore(expertAnnualAvgScores);

    }

    @Override
    public void updateEconomicAvg(List<ExpertAnnualAvgScore> expertAnnualAvgScores) {
        expertsAnnualAssMapper.updateEconomicAvg(expertAnnualAvgScores);

    }

    @Override
    public void updateEconomicDeptJudgesScore(List<ExpertAnnualAvgScore> expertAnnualAvgScores) {
        expertsAnnualAssMapper.updateEconomicDeptJudgesScore(expertAnnualAvgScores);

    }

    @Override
    public void updateEconomicBureauJudgesScore(List<ExpertAnnualAvgScore> expertAnnualAvgScores) {
        expertsAnnualAssMapper.updateEconomicBureauJudgesScore(expertAnnualAvgScores);

    }
}
