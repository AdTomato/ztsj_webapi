package com.authine.cloudpivot.web.api.service;

import com.authine.cloudpivot.web.api.bean.*;
import com.github.javafaker.App;

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


    public void updateAppraisalData(Appraisal ap);

    /**
     * 获取职代会测评表中的所有的民主测评表信息
     * @param id pId信息
     * @return 职代会测评表中的所有民主测评表
     */
    public List<DemocraticAppraisal> getAllDemocraticAppraisalByPId(String id);

    /**
     * 获取所有的发起职代会测评表中的“四好”领导班子评价表
     * @param id 发起职代会测评表id
     * @return 所有的发起职代会测评表中的“四好”领导班子评价表
     */
    public List<SACLeadBodyEvaluate> getAllSACLeadBodyEvaluateData(String id);

    /**
     * 更新所有的发起职代会测评表中的“四好”领导班子评价表
     * @param sacList 所有的发起职代会测评表中的“四好”领导班子评价表
     */
    public void updateAllSACLeadBodyEvaluateData(List<SACLeadBodyEvaluate> sacList);

    /**
     * 获取所有的职代会测评表中的“四好”领导班子评价表
     * @param id 发起职代会测评表id
     * @return 所有的职代会测评表中的“四好”领导班子评价表
     */
    public List<ACLeadBodyEvaluate> getAllACLeadBodyEvaluateData(String id);

    /**
     * 获取所有的发起职代会测评表中的领导班子民主测评表
     * @param id 发起职代会测评表id
     * @return 所有的发起职代会测评表中的领导班子民主测评表
     */
    public List<SACLeaderBodyAppraisal> getAllSACLeaderBodyAppraisalData(String id);

    /**
     * 更新所有的发起职代会测评表中的领导班子民主测评表
     * @param sacList 所有的发起职代会测评表中的领导班子民主测评表
     */
    public void updateAllSACLeaderBodyAppraisalData(List<SACLeaderBodyAppraisal> sacList);

    /**
     * 获取所有的职代会测评表中的领导班子民主测评表
     * @param id 发起职代会测评表id
     * @return 所有的职代会测评表中的领导班子民主测评表
     */
    public List<ACLeaderBodyAppraisal> getAllACLeaderBodyAppraisalData(String id);

    /**
     * 获取所有的发起职代会测评表中的领导班子党风廉政建设情况测评表
     * @param id 发起职代会测评表id
     * @return 所有的发起职代会测评表中的领导班子党风廉政建设情况测评表
     */
    public List<ASCHonestEvaluationForm> getAllASCHonestEvaluationFormData(String id);

    /**
     * 更新所有的发起职代会测评表中的领导班子党风廉政建设情况测评表
     * @param ascList 所有的发起职代会测评表中的领导班子党风廉政建设情况测评表
     */
    public void updateAllASCHonestEvaluationFormData(List<ASCHonestEvaluationForm> ascList);

    /**
     * 获取所有的职代会测评表中的领导班子党风廉政建设情况测评表
     * @param id 发起职代会测评表id
     * @return 所有的职代会测评表中的领导班子党风廉政建设情况测评表
     */
    public List<ACHonestEvaluationForm> getAllACHonestEvaluationFormData(String id);

    /**
     * 获取所有的发起职代会测评中的中铁四局领导人员落实党风廉政建设责任制和廉洁自律情况测评表
     * @param id 发起职代会测评表id
     * @return 所有的发起职代会测评中的中铁四局领导人员落实党风廉政建设责任制和廉洁自律情况测评表
     */
    public List<SACautonomicEvaluationForm> getAllSACautonomicEvaluationForm(String id);

    /**
     * 更新所有的发起职代会测评中的中铁四局领导人员落实党风廉政建设责任制和廉洁自律情况测评表
     * @param sacList 所有的发起职代会测评中的中铁四局领导人员落实党风廉政建设责任制和廉洁自律情况测评表
     */
    public void updateAllSACautonomicEvaluationForm(List<SACautonomicEvaluationForm> sacList);

    /**
     * 获取所有的职代会测评中的中铁四局领导人员落实党风廉政建设责任制和廉洁自律情况测评表
     * @param id 发起职代会测评表id
     * @return 所有的职代会测评中的中铁四局领导人员落实党风廉政建设责任制和廉洁自律情况测评表
     */
    public List<ACautonomicEvaluationForm> getAllACautonomicEvaluationForm(String id);

    /**
     * 更新发起职代会测评表主表信息
     * @param info 更新信息
     */
    public void updateStaffCongressEvaluationInfo(Map info);
}
