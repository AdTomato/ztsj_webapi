package com.authine.cloudpivot.web.api.mapper;

import com.authine.cloudpivot.web.api.bean.ExpertAnnualAvgScore;
import com.authine.cloudpivot.web.api.bean.ExpertsAssessInfo;
import com.authine.cloudpivot.web.api.bean.ExpertsInfos;

import java.util.List;

public interface ExpertsAnnualAssMapper {
     List<ExpertsInfos> findExportsList(ExpertsAssessInfo expertsAssessInfo);

    List<ExpertsInfos> findAllExportsList(ExpertsAssessInfo expertsAssessInfo);

    void resetFinancialauditannual(String parentId);

    void resetEngineerTechAnnual(String parentId);

    void resetEngineerEconomicAnnual(String parentId);

    List<ExpertAnnualAvgScore> countFinanceAvg(String id);

    void updateFinanceAvg(List<ExpertAnnualAvgScore> expertAnnualAvgScores);

    void updateFinanceDeptJudgesScore(List<ExpertAnnualAvgScore> expertAnnualAvgScores);

    List<ExpertAnnualAvgScore> countTechAvg(String id);

    void updateFinanceBureauJudgesScore(List<ExpertAnnualAvgScore> expertAnnualAvgScores);

    void updateTechAvg(List<ExpertAnnualAvgScore> expertAnnualAvgScores);

    void updateTechDeptJudgesScore(List<ExpertAnnualAvgScore> expertAnnualAvgScores);

    void updateTechBureauJudgesScore(List<ExpertAnnualAvgScore> expertAnnualAvgScores);

    List<ExpertAnnualAvgScore> countEconomicAvg(String id);

    void updateEconomicAvg(List<ExpertAnnualAvgScore> expertAnnualAvgScores);

    void updateEconomicDeptJudgesScore(List<ExpertAnnualAvgScore> expertAnnualAvgScores);

    void updateEconomicBureauJudgesScore(List<ExpertAnnualAvgScore> expertAnnualAvgScores);
}
