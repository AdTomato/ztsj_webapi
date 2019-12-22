package com.authine.cloudpivot.web.api.mapper;

import com.authine.cloudpivot.web.api.bean.Appraisal;

import java.util.List;

/**
 * @Author: wangyong
 * @Date: 2019-12-22 10:27
 * @Description: 职代会测评mapper
 */
public interface StaffCongressEvaluationMapper {

    /**
     * 获取班子民主测评表
     * @param parentId 班子民主测评表父id
     * @return 班子民主测评表集合
     */
    public List<Appraisal> getAllAppraisalData(String parentId);

}
