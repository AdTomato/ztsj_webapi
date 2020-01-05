package com.authine.cloudpivot.web.api.service.impl;

import com.authine.cloudpivot.web.api.bean.DemocraticEvaluation;
import com.authine.cloudpivot.web.api.bean.SDemocraticEvaluation;
import com.authine.cloudpivot.web.api.mapper.DemocraticEvaluationMapper;
import com.authine.cloudpivot.web.api.service.IDemocraticEvaluation;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @Author: wangyong
 * @Date: 2020-01-05 02:14
 * @Description: 民主评议表service实现类
 */
@Service
public class DemocraticEvaluationImpl implements IDemocraticEvaluation {

    @Resource
    DemocraticEvaluationMapper democraticEvaluationMapper;

    /**
     * @Author: wangyong
     * @Date: 2020/1/5 2:15
     * @param id : 发起工作发起工作民主评议表id
     * @return : com.authine.cloudpivot.web.api.bean.SDemocraticEvaluation
     * @Description: 获取发起工作民主评议表所有信息
     */
    @Override
    public SDemocraticEvaluation getSDemocraticEvaluationDataById(String id) {
        return democraticEvaluationMapper.getSDemocraticEvaluationDataById(id);
    }

    /**
     * @Author: wangyong
     * @Date: 2020/1/5 2:16
     * @param map : 参数
     * @return : java.util.List<com.authine.cloudpivot.web.api.bean.DemocraticEvaluation>
     * @Description: 根据传递过去的参数,获取符合规则的工作民主评议表
     */
    @Override
    public List<DemocraticEvaluation> getAllDemocraticEvaluationData(Map map) {
        return democraticEvaluationMapper.getAllDemocraticEvaluationData(map);
    }

    /**
     * @Author: wangyong
     * @Date: 2020/1/5 2:16
     * @param sd : 发起工作民主评议表
     * @return : void
     * @Description: 更新发起工作民主评议表所有信息
     */
    @Override
    public void updateSDemocraticEvaluation(SDemocraticEvaluation sd) {
        democraticEvaluationMapper.updateSDemocraticEvaluation(sd);
    }
}
