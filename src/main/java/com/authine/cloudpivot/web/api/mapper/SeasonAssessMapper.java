package com.authine.cloudpivot.web.api.mapper;

import com.authine.cloudpivot.web.api.bean.AvgScore;
import com.authine.cloudpivot.web.api.bean.TotalScore;
import com.authine.cloudpivot.web.api.bean.VoteInfo;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface SeasonAssessMapper {
    void saveScore(List<VoteInfo> voteInfo);

    int resetScore(String depteffectIds);

    List<AvgScore> countAvg(String id);

    int updateAvgScore(AvgScore avgScore);

    List<TotalScore> countTotal(String id);

    int updateTotalScore(TotalScore totalScore);

    int checkRepeat(String seasonassessmentId, String userId);

    void deleteWorkitemfinished(String instanceid);
}
