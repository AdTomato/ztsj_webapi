package com.authine.cloudpivot.web.api.mapper;

import com.authine.cloudpivot.web.api.bean.ExpertsDeclare;
import com.authine.cloudpivot.web.api.bean.ExpertsInfo;
import com.authine.cloudpivot.web.api.bean.ExpertsResultDetail;
import com.authine.cloudpivot.web.api.bean.ExpertsSelectResult;

import java.util.List;

/**
 * @Author:lfh
 * @Date: 2020/1/14 10:10
 * @Description：
 */

public interface ExpertsDeclareMapper {

    /**
     *
     * @param expertsInfo
     * @return
     */
    List<ExpertsSelectResult> getExpertsDeclareInfo(ExpertsInfo expertsInfo);

    /**
     * 清空每个专家的表决结果
     * @param parentId
     */
    void clearExpertsReult(String parentId);


    List<ExpertsResultDetail> findExpertsInfo(String id);

    void updateExpertPoll(List<ExpertsResultDetail> expertsResultDetails);

    String getExpertsDeclareStatus(String id);

    void updateAllExpertsDeclare(List<ExpertsDeclare> expertsDeclareList);
}
