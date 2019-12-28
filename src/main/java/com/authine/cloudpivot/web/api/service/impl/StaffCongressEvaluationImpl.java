package com.authine.cloudpivot.web.api.service.impl;

import com.authine.cloudpivot.web.api.bean.*;
import com.authine.cloudpivot.web.api.mapper.StaffCongressEvaluationMapper;
import com.authine.cloudpivot.web.api.service.IStaffCongressEvaluation;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

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
     * @Date: 2019/12/26 11:23
     * @param id : 发起只带会测评表id
     * @return : com.authine.cloudpivot.web.api.bean.StaffCongressEvaluation 职代会测评表信息
     * @Description: 获取发起职代会测评表的信息
     */
    @Override
    public StaffCongressEvaluation getStaffCongressEvaluationInfo(String id) {
        return staffCongressEvaluationMapper.getStaffCongressEvaluationInfo(id);
    }

    /**
     * @Author: wangyong
     * @Date: 2019/12/26 11:23
     * @param map : 参数集合
     * @return : java.util.List<java.lang.String> 职代会测评表的id集合
     * @Description: 根据unit获取从0到最大投票人数的职代会测评表的id
     */
    @Override
    public List<String> getAssessmentCongressData(Map map) {
        return staffCongressEvaluationMapper.getAssessmentCongressData(map);
    }

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

    /**
     * @Author: wangyong
     * @Date: 2019/12/26 11:24
     * @param list : 班子民主测评表
     * @return : void
     * @Description: 更新所有的发起职代会测评表中的班子民主测评表
     */
    @Override
    public void updateAllAppraisalData(List<Appraisal> list) {
        staffCongressEvaluationMapper.updateAllAppraisalData(list);
    }

    @Override
    public void updateAppraisalData(Appraisal ap) {
        staffCongressEvaluationMapper.updateAppraisalData(ap);
    }

    /**
     * @Author: wangyong
     * @Date: 2019/12/26 11:25
     * @param id : pId信息
     * @return : java.util.List<com.authine.cloudpivot.web.api.bean.DemocraticAppraisal> 职代会测评表中的所有民主测评表
     * @Description: 获取职代会测评表中的所有的民主测评表信息
     */
    @Override
    public List<DemocraticAppraisal> getAllDemocraticAppraisalByPId(String id) {
        return staffCongressEvaluationMapper.getAllDemocraticAppraisalByPId(id);
    }

    /**
     * @Author: wangyong
     * @Date: 2019/12/28 14:47
     * @param id : 发起职代会测评表id
     * @return : java.util.List<com.authine.cloudpivot.web.api.bean.SACLeadBodyEvaluate>
     * @Description: 获取所有的发起职代会测评表中的“四好”领导班子评价表
     */
    @Override
    public List<SACLeadBodyEvaluate> getAllSACLeadBodyEvaluateData(String id) {
        return staffCongressEvaluationMapper.getAllSACLeadBodyEvaluateData(id);
    }

    /**
     * @Author: wangyong
     * @Date: 2019/12/28 14:47
     * @param sacList : 所有的发起职代会测评表中的“四好”领导班子评价表
     * @return : void
     * @Description: 更新所有的发起职代会测评表中的“四好”领导班子评价表
     */
    @Override
    public void updateAllSACLeadBodyEvaluateData(List<SACLeadBodyEvaluate> sacList) {
        staffCongressEvaluationMapper.updateAllSACLeadBodyEvaluateData(sacList);
    }

    /**
     * @Author: wangyong
     * @Date: 2019/12/28 14:48
     * @param id : 发起职代会测评表id
     * @return : java.util.List<com.authine.cloudpivot.web.api.bean.ACLeadBodyEvaluate>
     * @Description: 获取所有的职代会测评表中的“四好”领导班子评价表
     */
    @Override
    public List<ACLeadBodyEvaluate> getAllACLeadBodyEvaluateData(String id) {
        return staffCongressEvaluationMapper.getAllACLeadBodyEvaluateData(id);
    }

    /**
     * @Author: wangyong
     * @Date: 2019/12/28 16:10
     * @param id : 发起职代会测评表id
     * @return : java.util.List<com.authine.cloudpivot.web.api.bean.SACLeaderBodyAppraisal>
     * @Description: 获取所有的发起职代会测评表中的领导班子民主测评表
     */
    @Override
    public List<SACLeaderBodyAppraisal> getAllSACLeaderBodyAppraisalData(String id) {
        return staffCongressEvaluationMapper.getAllSACLeaderBodyAppraisalData(id);
    }

    /**
     * @Author: wangyong
     * @Date: 2019/12/28 16:10
     * @param sacList : 所有的发起职代会测评表中的领导班子民主测评表
     * @return : void
     * @Description: 更新所有的发起职代会测评表中的领导班子民主测评表
     */
    @Override
    public void updateAllSACLeaderBodyAppraisalData(List<SACLeaderBodyAppraisal> sacList) {
        staffCongressEvaluationMapper.updateAllSACLeaderBodyAppraisalData(sacList);
    }

    /**
     * @Author: wangyong
     * @Date: 2019/12/28 16:11
     * @param id : 发起职代会测评表id
     * @return : java.util.List<com.authine.cloudpivot.web.api.bean.ACLeaderBodyAppraisal>
     * @Description: 获取所有的职代会测评表中的领导班子民主测评表
     */
    @Override
    public List<ACLeaderBodyAppraisal> getAllACLeaderBodyAppraisalData(String id) {
        return staffCongressEvaluationMapper.getAllACLeaderBodyAppraisalData(id);
    }
}
