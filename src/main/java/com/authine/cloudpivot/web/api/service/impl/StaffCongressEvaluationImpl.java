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
     * @param id : 发起只带会测评表id
     * @return : com.authine.cloudpivot.web.api.bean.StaffCongressEvaluation 职代会测评表信息
     * @Author: wangyong
     * @Date: 2019/12/26 11:23
     * @Description: 获取发起职代会测评表的信息
     */
    @Override
    public StaffCongressEvaluation getStaffCongressEvaluationInfo(String id) {
        return staffCongressEvaluationMapper.getStaffCongressEvaluationInfo(id);
    }

    /**
     * @param map : 参数集合
     * @return : java.util.List<java.lang.String> 职代会测评表的id集合
     * @Author: wangyong
     * @Date: 2019/12/26 11:23
     * @Description: 根据unit获取从0到最大投票人数的职代会测评表的id
     */
    @Override
    public List<String> getAssessmentCongressData(Map map) {
        return staffCongressEvaluationMapper.getAssessmentCongressData(map);
    }

    /**
     * @param parentId : 职代会测评id值
     * @return : java.util.List<com.authine.cloudpivot.web.api.bean.Appraisal>
     * @Author: wangyong
     * @Date: 2019/12/22 10:51
     * @Description: 根据职代会测评id值获取班子民主测评集合
     */
    @Override
    public List<Appraisal> getAllAppraisalData(String parentId) {
        return staffCongressEvaluationMapper.getAllAppraisalData(parentId);
    }

    /**
     * @param list : 班子民主测评表
     * @return : void
     * @Author: wangyong
     * @Date: 2019/12/26 11:24
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
     * @param id : pId信息
     * @return : java.util.List<com.authine.cloudpivot.web.api.bean.DemocraticAppraisal> 职代会测评表中的所有民主测评表
     * @Author: wangyong
     * @Date: 2019/12/26 11:25
     * @Description: 获取职代会测评表中的所有的民主测评表信息
     */
    @Override
    public List<DemocraticAppraisal> getAllDemocraticAppraisalByPId(String id) {
        return staffCongressEvaluationMapper.getAllDemocraticAppraisalByPId(id);
    }

    /**
     * @param id : 发起职代会测评表id
     * @return : java.util.List<com.authine.cloudpivot.web.api.bean.SACLeadBodyEvaluate>
     * @Author: wangyong
     * @Date: 2019/12/28 14:47
     * @Description: 获取所有的发起职代会测评表中的“四好”领导班子评价表
     */
    @Override
    public List<SACLeadBodyEvaluate> getAllSACLeadBodyEvaluateData(String id) {
        return staffCongressEvaluationMapper.getAllSACLeadBodyEvaluateData(id);
    }

    /**
     * @param sacList : 所有的发起职代会测评表中的“四好”领导班子评价表
     * @return : void
     * @Author: wangyong
     * @Date: 2019/12/28 14:47
     * @Description: 更新所有的发起职代会测评表中的“四好”领导班子评价表
     */
    @Override
    public void updateAllSACLeadBodyEvaluateData(List<SACLeadBodyEvaluate> sacList) {
        staffCongressEvaluationMapper.updateAllSACLeadBodyEvaluateData(sacList);
    }

    /**
     * @param id : 发起职代会测评表id
     * @return : java.util.List<com.authine.cloudpivot.web.api.bean.ACLeadBodyEvaluate>
     * @Author: wangyong
     * @Date: 2019/12/28 14:48
     * @Description: 获取所有的职代会测评表中的“四好”领导班子评价表
     */
    @Override
    public List<ACLeadBodyEvaluate> getAllACLeadBodyEvaluateData(String id) {
        return staffCongressEvaluationMapper.getAllACLeadBodyEvaluateData(id);
    }

    /**
     * @param id : 发起职代会测评表id
     * @return : java.util.List<com.authine.cloudpivot.web.api.bean.SACLeaderBodyAppraisal>
     * @Author: wangyong
     * @Date: 2019/12/28 16:10
     * @Description: 获取所有的发起职代会测评表中的领导班子民主测评表
     */
    @Override
    public List<SACLeaderBodyAppraisal> getAllSACLeaderBodyAppraisalData(String id) {
        return staffCongressEvaluationMapper.getAllSACLeaderBodyAppraisalData(id);
    }

    /**
     * @param sacList : 所有的发起职代会测评表中的领导班子民主测评表
     * @return : void
     * @Author: wangyong
     * @Date: 2019/12/28 16:10
     * @Description: 更新所有的发起职代会测评表中的领导班子民主测评表
     */
    @Override
    public void updateAllSACLeaderBodyAppraisalData(List<SACLeaderBodyAppraisal> sacList) {
        staffCongressEvaluationMapper.updateAllSACLeaderBodyAppraisalData(sacList);
    }

    /**
     * @param id : 发起职代会测评表id
     * @return : java.util.List<com.authine.cloudpivot.web.api.bean.ACLeaderBodyAppraisal>
     * @Author: wangyong
     * @Date: 2019/12/28 16:11
     * @Description: 获取所有的职代会测评表中的领导班子民主测评表
     */
    @Override
    public List<ACLeaderBodyAppraisal> getAllACLeaderBodyAppraisalData(String id) {
        return staffCongressEvaluationMapper.getAllACLeaderBodyAppraisalData(id);
    }

    /**
     * @param id : 发起职代会测评表id
     * @return : java.util.List<com.authine.cloudpivot.web.api.bean.ASCHonestEvaluationForm>
     * @Author: wangyong
     * @Date: 2019/12/28 17:22
     * @Description: 获取所有的发起职代会测评表中的领导班子党风廉政建设情况测评表
     */
    @Override
    public List<ASCHonestEvaluationForm> getAllASCHonestEvaluationFormData(String id) {
        return staffCongressEvaluationMapper.getAllASCHonestEvaluationFormData(id);
    }

    /**
     * @param ascList : 所有的发起职代会测评表中的领导班子党风廉政建设情况测评表
     * @return : void
     * @Author: wangyong
     * @Date: 2019/12/28 17:22
     * @Description: 更新所有的发起职代会测评表中的领导班子党风廉政建设情况测评表
     */
    @Override
    public void updateAllASCHonestEvaluationFormData(List<ASCHonestEvaluationForm> ascList) {
        staffCongressEvaluationMapper.updateAllASCHonestEvaluationFormData(ascList);
    }

    /**
     * @param id : 发起职代会测评表id
     * @return : java.util.List<com.authine.cloudpivot.web.api.bean.ACHonestEvaluationForm>
     * @Author: wangyong
     * @Date: 2019/12/28 17:22
     * @Description: 所有的职代会测评表中的领导班子党风廉政建设情况测评表
     */
    @Override
    public List<ACHonestEvaluationForm> getAllACHonestEvaluationFormData(String id) {
        return staffCongressEvaluationMapper.getAllACHonestEvaluationFormData(id);
    }

    /**
     * @param id : 发起职代会测评表id
     * @return : java.util.List<com.authine.cloudpivot.web.api.bean.SACautonomicEvaluationForm>
     * @Author: wangyong
     * @Date: 2019/12/30 10:57
     * @Description: 获取所有的发起职代会测评中的中铁四局领导人员落实党风廉政建设责任制和廉洁自律情况测评表
     */
    @Override
    public List<SACautonomicEvaluationForm> getAllSACautonomicEvaluationForm(String id) {
        return staffCongressEvaluationMapper.getAllSACautonomicEvaluationForm(id);
    }

    /**
     * @param sacList : 所有的发起职代会测评中的中铁四局领导人员落实党风廉政建设责任制和廉洁自律情况测评表
     * @return : void
     * @Author: wangyong
     * @Date: 2019/12/30 10:57
     * @Description: 更新所有的发起职代会测评中的中铁四局领导人员落实党风廉政建设责任制和廉洁自律情况测评表
     */
    @Override
    public void updateAllSACautonomicEvaluationForm(List<SACautonomicEvaluationForm> sacList) {
        staffCongressEvaluationMapper.updateAllSACautonomicEvaluationForm(sacList);
    }

    /**
     * @param id : 发起职代会测评表id
     * @return : java.util.List<com.authine.cloudpivot.web.api.bean.ACHonestEvaluationForm>
     * @Author: wangyong
     * @Date: 2019/12/30 10:57
     * @Description: 获取所有的职代会测评中的中铁四局领导人员落实党风廉政建设责任制和廉洁自律情况测评表
     */
    @Override
    public List<ACautonomicEvaluationForm> getAllACautonomicEvaluationForm(String id) {
        return staffCongressEvaluationMapper.getAllACautonomicEvaluationForm(id);
    }

    /**
     * @param info : 更新信息
     * @return : void
     * @Author: wangyong
     * @Date: 2019/12/30 11:54
     * @Description: 更新发起职代会测评表主表信息
     */
    @Override
    public void updateStaffCongressEvaluationInfo(Map info) {
        staffCongressEvaluationMapper.updateStaffCongressEvaluationInfo(info);
    }

    /**
     * @param id : 发起职代会测评表id
     * @return : java.lang.String 是:不可提交,否:可提交
     * @Author: wangyong
     * @Date: 2019/12/31 14:55
     * @Description: 是否可以提交
     */
    @Override
    public String isCanSubmit(String id) {
        return staffCongressEvaluationMapper.isCanSubmit(id);
    }
}
