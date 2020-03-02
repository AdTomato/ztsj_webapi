package com.authine.cloudpivot.web.api.service;

import com.authine.cloudpivot.web.api.bean.ExpertsAssessInfo;
import com.authine.cloudpivot.web.api.bean.ExpertsInfos;
import com.authine.cloudpivot.web.api.bean.ExpertAnnualAvgScore;

import java.util.List;

public interface ExpertsAnnualAssService {
    List<ExpertsInfos> findExportsList(ExpertsAssessInfo expertsAssessInfo);

    List<ExpertsInfos> findAllExportsList(ExpertsAssessInfo expertsAssessInfo);

    void resetFinancialauditannual(String parentId);

    void resetEngineerTechAnnual(String parentId);

    void resetEngineerEconomicAnnual(String parentId);

    List<ExpertAnnualAvgScore> countFinanceAvg(String id);
    List<ExpertAnnualAvgScore> countTechAvg(String id);
    List<ExpertAnnualAvgScore> countEconomicAvg(String id);

    void updateFinanceAvg(List<ExpertAnnualAvgScore> expertAnnualAvgScores);

    void updateFinanceDeptJudgesScore(List<ExpertAnnualAvgScore> expertAnnualAvgScores);
    void updateFinanceBureauJudgesScore(List<ExpertAnnualAvgScore> expertAnnualAvgScores);

    void updateTechAvg(List<ExpertAnnualAvgScore> expertAnnualAvgScores);

    void updateTechDeptJudgesScore(List<ExpertAnnualAvgScore> expertAnnualAvgScores);

    void updateTechBureauJudgesScore(List<ExpertAnnualAvgScore> expertAnnualAvgScores);

    void updateEconomicAvg(List<ExpertAnnualAvgScore> expertAnnualAvgScores);

    void updateEconomicDeptJudgesScore(List<ExpertAnnualAvgScore> expertAnnualAvgScores);

    void updateEconomicBureauJudgesScore(List<ExpertAnnualAvgScore> expertAnnualAvgScores);
}
