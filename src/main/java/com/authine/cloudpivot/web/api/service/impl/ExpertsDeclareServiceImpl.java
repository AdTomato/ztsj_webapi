package com.authine.cloudpivot.web.api.service.impl;

import com.authine.cloudpivot.web.api.bean.*;
import com.authine.cloudpivot.web.api.mapper.ExpertsDeclareMapper;
import com.authine.cloudpivot.web.api.service.ExpertsDeclareService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author:lfh
 * @Date: 2020/1/14 10:07
 * @Description：
 */
@Service
public class ExpertsDeclareServiceImpl implements ExpertsDeclareService {

    @Resource
    private ExpertsDeclareMapper expertsDeclareMapper;

    /**
     * 根据条件获取符合条件的全部专家信息
     * @param expertsInfo
     * @return
     */
    @Override
    public List<ExpertsSelectResult> getExpertsDeclareInfo(ExpertsInfo expertsInfo) {
        return expertsDeclareMapper.getExpertsDeclareInfo(expertsInfo);
    }

    /**
     * 清空每个专家的表决结果
     * @param parentId
     */
    @Override
    public void clearExpertsReult(String parentId) {
        expertsDeclareMapper.clearExpertsReult(parentId);
    }

    /**
     * 查询全部专家明细信息
     * @param id
     * @return
     */
    @Override
    public List<ExpertsResultDetail> findExpertsInfo(String id) {
        return expertsDeclareMapper.findExpertsInfo(id);
    }

    @Override
    public String getExpertsDeclareStatus(String id) {
        return expertsDeclareMapper.getExpertsDeclareStatus(id);
    }

    /**
     * 更新每个专家的投票结果
     * @param expertsDeclareList
     */
    @Override
    public void updateAllExpertsDeclare(List<ExpertsDeclare> expertsDeclareList) {
        expertsDeclareMapper.updateAllExpertsDeclare(expertsDeclareList);
    }

    /**
     * 通过姓名和单位获取全部通过的专家
     * @param expertDeclareName,expertsDeclareOrganization
     * @return
     */
    @Override
    public ExpertsInfoList findExpertsFromInfoList(String expertDeclareName,String expertsDeclareOrganization) {
        Map<String,Object> map = new HashMap<>();
        map.put("userName",expertDeclareName );
        map.put("unit", expertsDeclareOrganization);
        return expertsDeclareMapper.findExpertsFromInfoList(map);
    }

    /**
     * 通过专家id获取专家的参评条件
     * @param parentId
     * @return
     */
    @Override
    public List<ConditionsParticipations> getConditionsParticipations(String parentId) {
        return expertsDeclareMapper.getConditionsParticipations(parentId);
    }

    //插入参评条件子表信息
    @Override
    public void insertConditions(List<ConditionsParticipations> conditionsParticipations) {
        expertsDeclareMapper.insertConditions(conditionsParticipations);
    }

    @Override
    @Transactional
    public List<String> getExpertsDeclareDetailsNum(String id) {
         return expertsDeclareMapper.getExpertsDeclareDetailsNum(id);
    }
}
