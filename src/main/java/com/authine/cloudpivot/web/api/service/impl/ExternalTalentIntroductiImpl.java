package com.authine.cloudpivot.web.api.service.impl;

import com.authine.cloudpivot.web.api.bean.InterviewCondition;
import com.authine.cloudpivot.web.api.bean.IntroduceEvaluation;
import com.authine.cloudpivot.web.api.bean.IntroduceEvaluationSum;
import com.authine.cloudpivot.web.api.mapper.ExternalTalentIntroductiMapper;
import com.authine.cloudpivot.web.api.service.IExternalTalentIntroducti;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: wangyong
 * @Date: 2020-01-17 17:23
 * @Description:
 */
@Service
public class ExternalTalentIntroductiImpl implements IExternalTalentIntroducti {

    @Resource
    ExternalTalentIntroductiMapper externalTalentIntroductiMapper;

    @Override
    public void insertInterviewCondition(List<InterviewCondition> interviewConditions) {
        externalTalentIntroductiMapper.insertInterviewCondition(interviewConditions);
    }

    @Override
    public List<IntroduceEvaluation> getAllIntroduceEvaluationByUserName(String userName) {
        return externalTalentIntroductiMapper.getAllIntroduceEvaluationByUserName(userName);
    }

    @Override
    public IntroduceEvaluationSum getIntroduceEvaluationSumByUserName(String userName) {
        return externalTalentIntroductiMapper.getIntroduceEvaluationSumByUserName(userName);
    }

    @Override
    public List<InterviewCondition> getAllSEInterviewCondition(String userName) {
        return externalTalentIntroductiMapper.getAllSEInterviewCondition(userName);
    }

    @Override
    public List<InterviewCondition> getAllInterviewCondition(String userName) {
        return externalTalentIntroductiMapper.getAllInterviewCondition(userName);
    }

    @Override
    public void updateIntroduceEvaluationSum(IntroduceEvaluationSum introduceEvaluationSum) {
        externalTalentIntroductiMapper.updateIntroduceEvaluationSum(introduceEvaluationSum);
    }

    @Override
    public void updateAllInterviewCondition(List<InterviewCondition> interviewConditionList) {
        externalTalentIntroductiMapper.updateAllInterviewCondition(interviewConditionList);
    }

    @Override
    public List<String> weatherHaveIntroduceEvaluationSumByUserName(String userName) {
        return externalTalentIntroductiMapper.weatherHaveIntroduceEvaluationSumByUserName(userName);
    }
}
