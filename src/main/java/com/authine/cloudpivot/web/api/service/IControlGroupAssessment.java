package com.authine.cloudpivot.web.api.service;

import com.authine.cloudpivot.engine.api.facade.BizObjectFacade;
import com.authine.cloudpivot.web.api.bean.ControlGroupDetail;

import java.util.List;
import java.util.Map;

/**
 * @Author: wangyong
 * @Date: 2020-01-10 14:20
 * @Description: 管控组负责人年度考核汇总表
 */
public interface IControlGroupAssessment {

    String getControlGroupAssessmentIdByAnnual(String annual);

    void insertControlGroupDetails(List<ControlGroupDetail> controlGroupDetailList);

    void insertControlGroupDetail(ControlGroupDetail controlGroupDetail);

    void getControlGroupDetailById(String id);

    void updateControlGroupDetailById(ControlGroupDetail controlGroupDetail);

    void calculateControlGroupAssessmentScore(String annual, String userId, BizObjectFacade bizObjectFacade, ControlGroupDetail controlGroupDetail);

    String createControlGroupAssessment(BizObjectFacade bizObjectFacade, String annual, String userId);

    ControlGroupDetail getControlGroupDetailByIdAndAssessee(Map map);
}
