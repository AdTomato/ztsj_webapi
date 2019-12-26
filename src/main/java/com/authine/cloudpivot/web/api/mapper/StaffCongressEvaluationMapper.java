package com.authine.cloudpivot.web.api.mapper;

import com.authine.cloudpivot.web.api.bean.Appraisal;
import com.authine.cloudpivot.web.api.bean.DemocraticAppraisal;
import com.authine.cloudpivot.web.api.bean.StaffCongressEvaluation;

import java.util.List;
import java.util.Map;

/**
 * @Author: wangyong
 * @Date: 2019-12-22 10:27
 * @Description: 职代会测评mapper
 */
public interface StaffCongressEvaluationMapper {

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
     * 获取发起职代会测评表中的班子民主测评表
     * @param id 班子民主测评表父id
     * @return 班子民主测评表集合
     */
    public List<Appraisal> getAllAppraisalData(String id);

    /**
     * 更新发起职代会测评表中的班子民主测评表
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
