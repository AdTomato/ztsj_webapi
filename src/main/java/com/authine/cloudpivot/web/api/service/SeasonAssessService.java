package com.authine.cloudpivot.web.api.service;

import com.authine.cloudpivot.web.api.bean.AvgScore;
import com.authine.cloudpivot.web.api.bean.TotalScore;
import com.authine.cloudpivot.web.api.bean.VoteInfo;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * @author zsh
 * @since 2019-12-03
 */
public interface SeasonAssessService {
    void saveScore(List<VoteInfo> voteInfo);

    int resetScore(String depteffectIds);

    List<AvgScore> countAvg(String id);

    int updateAvg(AvgScore avgScore);

    List<TotalScore> countTotal(String id);

    int updateTotal(TotalScore totalScore);
}
