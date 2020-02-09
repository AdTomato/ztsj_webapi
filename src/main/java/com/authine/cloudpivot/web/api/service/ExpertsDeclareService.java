package com.authine.cloudpivot.web.api.service;

import com.authine.cloudpivot.web.api.bean.*;

import java.util.List;

/**
 * @Author:lfh
 * @Date: 2020/1/13 17:15
 * @Description：
 */

public interface ExpertsDeclareService {
    /**
     * 根据条件获取符合条件的全部专家信息
     *
     * @param expertsInfo
     * @return
     */
    List<ExpertsSelectResult> getExpertsDeclareInfo(ExpertsInfo expertsInfo);

    /**
     * 清空每个专家的表决结果
     *
     * @param parentId
     */
    void clearExpertsReult(String parentId);

    //查询全部的专家明细信息
    List<ExpertsResultDetail> findExpertsInfo(String id);

    String getExpertsDeclareStatus(String id);

    /**
     * 更新每个专家的投票结果
     *
     * @param expertsDeclareList
     */
    void updateAllExpertsDeclare(List<ExpertsDeclare> expertsDeclareList);

    /**
     * 通过投票结果获取全部通过的专家装填
     *
     * @param expertDeclareName,expertsDeclareOrganization
     * @return
     */
    List<ExpertsInfoList> findExpertsFromInfoList(String expertDeclareName, String expertsDeclareOrganization);

    /**
     * 通过专家id获取专家的参评条件
     *
     * @param parentId
     * @return
     */
    List<ConditionsParticipations> getConditionsParticipations(String parentId);


    /**
     * @param expertDeclareName          专家姓名
     * @param expertsDeclareOrganization 单位
     * @return 专家信息
     */
    List<ExpertDeclareInfo> findExpertsFromExpertDeclare(String expertDeclareName, String expertsDeclareOrganization);


    /**
     * 插入参评条件
     *
     * @param conditionsParticipations
     */
    void insertConditions(List<ConditionsParticipations> conditionsParticipations);
}
