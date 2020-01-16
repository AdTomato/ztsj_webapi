package com.authine.cloudpivot.web.api.service.impl;

import com.authine.cloudpivot.web.api.bean.ExpertsDeclare;
import com.authine.cloudpivot.web.api.bean.ExpertsInfo;
import com.authine.cloudpivot.web.api.bean.ExpertsResultDetail;
import com.authine.cloudpivot.web.api.bean.ExpertsSelectResult;
import com.authine.cloudpivot.web.api.mapper.ExpertsDeclareMapper;
import com.authine.cloudpivot.web.api.service.ExpertsDeclareService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

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

    //查询全部专家明细信息
    @Override
    public List<ExpertsResultDetail> findExpertsInfo(String id) {
        return expertsDeclareMapper.findExpertsInfo(id);
    }

    @Override
    public void updateExpertPoll(List<ExpertsResultDetail> expertsResultDetails) {
        expertsDeclareMapper.updateExpertPoll(expertsResultDetails);
    }

    @Override
    public String getExpertsDeclareStatus(String id) {
        return expertsDeclareMapper.getExpertsDeclareStatus(id);
    }

    @Override
    public void updateAllExpertsDeclare(List<ExpertsDeclare> expertsDeclareList) {
        expertsDeclareMapper.updateAllExpertsDeclare(expertsDeclareList);
    }
}
