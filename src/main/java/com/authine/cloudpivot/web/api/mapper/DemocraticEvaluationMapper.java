package com.authine.cloudpivot.web.api.mapper;

import com.authine.cloudpivot.web.api.bean.DemocraticEvaluation;
import com.authine.cloudpivot.web.api.bean.SDemocraticEvaluation;

import java.util.List;
import java.util.Map;

/**
 * @Author: wangyong
 * @Date: 2020-01-05 01:58
 * @Description: 工作民主评议表mapper
 */
public interface DemocraticEvaluationMapper {

    /**
     * 获取发起工作民主评议表所有信息
     *
     * @param id 发起工作发起工作民主评议表id
     * @return 发起工作民主评议表信息
     */
    public SDemocraticEvaluation getSDemocraticEvaluationDataById(String id);

    /**
     * 根据传递过去的参数,获取符合规则的工作民主评议表
     *
     * @param map 参数
     * @return 符合规则的所有的工作民主评议表
     */
    public List<DemocraticEvaluation> getAllDemocraticEvaluationData(Map map);

    /**
     * 更新发起工作民主评议表所有信息
     *
     * @param sd 发起工作民主评议表
     */
    public void updateSDemocraticEvaluation(SDemocraticEvaluation sd);

}
