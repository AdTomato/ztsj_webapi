package com.authine.cloudpivot.web.api.service.impl;

import com.authine.cloudpivot.web.api.bean.AvgScore;
import com.authine.cloudpivot.web.api.bean.TotalScore;
import com.authine.cloudpivot.web.api.bean.VoteInfo;
import com.authine.cloudpivot.web.api.mapper.SeasonAssessMapper;
import com.authine.cloudpivot.web.api.service.SeasonAssessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Service
public class SeasonAssessServiceImpl implements SeasonAssessService {

    @Resource
    private SeasonAssessMapper seasonAssessMapper;

    @Override
    public void saveScore(List<VoteInfo> voteInfo) {
        seasonAssessMapper.saveScore(voteInfo);
    }

    @Override
    public int resetScore(String depteffectIds) {
        return seasonAssessMapper.resetScore(depteffectIds);
    }

    @Override
    public List<AvgScore> countAvg(String id) {
        return seasonAssessMapper.countAvg(id);
    }

    @Override
    public int updateAvg(AvgScore avgScore) {
        return seasonAssessMapper.updateAvgScore(avgScore);
    }

    @Override
    public List<TotalScore> countTotal(String id) {
        return seasonAssessMapper.countTotal(id);
    }

    @Override
    public int updateTotal(TotalScore totalScore) {
        return seasonAssessMapper.updateTotalScore(totalScore);
    }

    @Override
    public int checkRepeat(String seasonassessmentId, String userId) {
        return seasonAssessMapper.checkRepeat(seasonassessmentId, userId);
    }

    @Override
    public void deleteWorkitemfinished(String instanceid) {
        seasonAssessMapper.deleteWorkitemfinished(instanceid);
    }
}
