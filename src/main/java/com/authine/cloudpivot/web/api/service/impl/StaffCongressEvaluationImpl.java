package com.authine.cloudpivot.web.api.service.impl;

import com.authine.cloudpivot.web.api.bean.Appraisal;
import com.authine.cloudpivot.web.api.mapper.StaffCongressEvaluationMapper;
import com.authine.cloudpivot.web.api.service.IStaffCongressEvaluation;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: wangyong
 * @Date: 2019-12-22 10:50
 * @Description: 职代会测评service
 */
@Service
public class StaffCongressEvaluationImpl implements IStaffCongressEvaluation {

    @Resource
    StaffCongressEvaluationMapper staffCongressEvaluationMapper;

    /**
     * @Author: wangyong
     * @Date: 2019/12/22 10:51
     * @param parentId : 职代会测评id值
     * @return : java.util.List<com.authine.cloudpivot.web.api.bean.Appraisal>
     * @Description: 根据职代会测评id值获取班子民主测评集合
     */
    @Override
    public List<Appraisal> getAllAppraisalData(String parentId) {
        return staffCongressEvaluationMapper.getAllAppraisalData(parentId);
    }
}
