package com.authine.cloudpivot.web.api.service;

import com.authine.cloudpivot.web.api.bean.InterviewCondition;
import com.authine.cloudpivot.web.api.bean.IntroduceEvaluation;
import com.authine.cloudpivot.web.api.bean.IntroduceEvaluationSum;

import java.util.List;

/**
 * @Author: wangyong
 * @Date: 2020-01-17 17:22
 * @Description:
 */
public interface IExternalTalentIntroducti {

    public void insertInterviewCondition(List<InterviewCondition> interviewConditions);

    public List<IntroduceEvaluation> getAllIntroduceEvaluationByUserName(String userName);

    public IntroduceEvaluationSum getIntroduceEvaluationSumByUserName(String userName);

    public List<InterviewCondition> getAllSEInterviewCondition(String userName);

    public List<InterviewCondition> getAllInterviewCondition(String userName);

    public void updateIntroduceEvaluationSum(IntroduceEvaluationSum introduceEvaluationSum);

    public void updateAllInterviewCondition(List<InterviewCondition> interviewConditionList);

    public List<String> weatherHaveIntroduceEvaluationSumByUserName(String userName);
}
