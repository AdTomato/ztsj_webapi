package com.authine.cloudpivot.web.api.service;

import com.authine.cloudpivot.web.api.bean.Appraisal;
import com.authine.cloudpivot.web.api.bean.DemocraticAppraisal;
import com.authine.cloudpivot.web.api.bean.StaffCongressEvaluation;

import java.util.List;
import java.util.Map;

/**
 * @Author: wangyong
 * @Date: 2019-12-22 10:49
 * @Description: 职代会测评service接口
 */
public interface IStaffCongressEvaluation {

    /**
     * 获取发起职代会测评表的信息
     * @param id 发起只带会测评表id
     * @return 职代会测评表信息
     */
    public StaffCongressEvaluation getStaffCongressEvaluationInfo(String id);

    /**
     * 根据unit获取从0到最大投票人数的职代会测评表的id
     * @param map 参数集合
     * @return 职代会测评表的id集合
     */
    public List<String> getAssessmentCongressData(Map map);

    /**
     * 获取班子民主测评表
     * @param parentId 班子民主测评表父id
     * @return 班子民主测评表集合
     */
    public List<Appraisal> getAllAppraisalData(String parentId);

    /**
     * 更新所有的发起职代会测评表中的班子民主测评表
     * @param list 班子民主测评表
     */
    public void updateAllAppraisalData(List<Appraisal> list);

    /**
     * 获取职代会测评表中的所有的民主测评表信息
     * @param id pId信息
     * @return 职代会测评表中的所有民主测评表
     */
    public List<DemocraticAppraisal> getAllDemocraticAppraisalByPId(String id);

}
