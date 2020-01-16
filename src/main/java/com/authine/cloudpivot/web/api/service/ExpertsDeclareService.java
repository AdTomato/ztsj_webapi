package com.authine.cloudpivot.web.api.service;

import com.authine.cloudpivot.web.api.bean.ExpertsDeclare;
import com.authine.cloudpivot.web.api.bean.ExpertsInfo;
import com.authine.cloudpivot.web.api.bean.ExpertsResultDetail;
import com.authine.cloudpivot.web.api.bean.ExpertsSelectResult;

import java.util.List;

/**
 * @Author:lfh
 * @Date: 2020/1/13 17:15
 * @Description：
 */

public interface ExpertsDeclareService {
    /**
     * 根据条件获取符合条件的全部专家信息
     * @param expertsInfo
     * @return
     */
    List<ExpertsSelectResult> getExpertsDeclareInfo(ExpertsInfo expertsInfo);

    /**
     * 清空每个专家的表决结果
     * @param parentId
     */
    void clearExpertsReult(String parentId);

    //查询全部的专家明细信息
    List<ExpertsResultDetail> findExpertsInfo(String id);

    void updateExpertPoll(List<ExpertsResultDetail> expertsResultDetails);

    String getExpertsDeclareStatus(String id);

    void updateAllExpertsDeclare(List<ExpertsDeclare> expertsDeclareList);
}
