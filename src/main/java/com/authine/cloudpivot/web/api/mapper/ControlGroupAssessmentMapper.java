package com.authine.cloudpivot.web.api.mapper;

import com.authine.cloudpivot.web.api.bean.ControlGroupDetail;

import java.util.List;
import java.util.Map;

/**
 * @Author: wangyong
 * @Date: 2020-01-10 00:48
 * @Description: 管控组负责人年度考核汇总mapper
 */
public interface ControlGroupAssessmentMapper {

    String getControlGroupAssessmentIdByAnnual(String annual);

    void insertControlGroupDetails(List<ControlGroupDetail> controlGroupDetailList);

    void insertControlGroupDetail(ControlGroupDetail controlGroupDetail);

    void getControlGroupDetailById(String id);

    void updateControlGroupDetailById(ControlGroupDetail controlGroupDetail);

    ControlGroupDetail getControlGroupDetailByIdAndAssessee(Map map);

}
