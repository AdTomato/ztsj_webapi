package com.authine.cloudpivot.web.api.mapper;

import com.authine.cloudpivot.web.api.bean.InterviewCondition;
import com.authine.cloudpivot.web.api.bean.IntroduceEvaluation;
import com.authine.cloudpivot.web.api.bean.IntroduceEvaluationSum;

import java.util.List;

/**
 * @Author: wangyong
 * @Date: 2020-01-17 17:13
 * @Description: 紧缺人才外部引进招聘mapper
 */
public interface ExternalTalentIntroductiMapper {

    public void insertInterviewCondition(List<InterviewCondition> interviewConditions);

    /**
     * 根据用户名称获取
     *
     * @param userName
     * @return
     */
    public List<IntroduceEvaluation> getAllIntroduceEvaluationByUserName(String userName);

    public IntroduceEvaluationSum getIntroduceEvaluationSumByUserName(String userName);

    public void updateIntroduceEvaluationSum(IntroduceEvaluationSum introduceEvaluationSum);

    public List<InterviewCondition> getAllSEInterviewCondition(String userName);

    public List<InterviewCondition> getAllInterviewCondition(String userName);

    public void updateAllInterviewCondition(List<InterviewCondition> interviewConditionList);

    public List<String> weatherHaveIntroduceEvaluationSumByUserName(String userName);

}
