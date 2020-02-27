package com.authine.cloudpivot.web.api.service;

import com.authine.cloudpivot.engine.api.facade.BizObjectFacade;
import com.authine.cloudpivot.web.api.bean.AssessmentResult;

import java.util.List;

/**
 * @Author: wangyong
 * @Date: 2019-12-27 09:37
 * @Description:
 */
public interface ICreateAssessmentResult {

    public List<String> createAssessmentResults(BizObjectFacade objectFacade, String userId, List<AssessmentResult> arList);

    public void createAssessmentResult(BizObjectFacade objectFacade, String userId, AssessmentResult assessmentResult);


    public String isHaveAssessmentResult(AssessmentResult ar);

    public void updateAssessmentResult(List<AssessmentResult> arList);

}
