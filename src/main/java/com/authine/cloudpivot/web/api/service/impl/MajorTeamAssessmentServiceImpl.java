package com.authine.cloudpivot.web.api.service.impl;

import com.authine.cloudpivot.web.api.bean.*;
import com.authine.cloudpivot.web.api.mapper.MajorTeamAssessmentMapper;
import com.authine.cloudpivot.web.api.service.MajorTeamAssessmentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @Author:lfh
 * @Date: 2020/1/1 11:42
 * @Description：班子考核service
 */
@Service
public class MajorTeamAssessmentServiceImpl implements MajorTeamAssessmentService {

    @Resource
    MajorTeamAssessmentMapper majorTeamAssessmentMapper;

    /**
     * 根据id获取发起班子考核表中主表的所有信息
     *
     * @param id
     * @return
     */
    @Override
    public MajorTeamAssessment getMajorTeamAssessmentInfo(String id) {
        return majorTeamAssessmentMapper.getMajorTeamAssessmentInfo(id);
    }

    /**
     * //根据unit获取从0到最大投票人数的班子考核表的id
     *
     * @param map
     * @return List<String>班子考核表中的id
     */
    @Override
    public List<String> getMajorTeamIdByUnit(Map map) {
        return majorTeamAssessmentMapper.getMajorTeamIdByUnit(map);
    }

    /**
     * 更新发起班子考核主表的信息
     *
     * @param info
     */
    @Override
    public void updatemajorTeamAssessmentInfo(Map<String, Object> info) {
        majorTeamAssessmentMapper.updatemajorTeamAssessmentInfo(info);
    }

    /**
     * 获取全部的发起班子考核的领导班子民主测评表
     *
     * @param id 父id
     * @return 根据班子考核表id获取领导班子民主测评表集合
     */
    @Override
    public List<SMTALeaderBodyAppraisal> getAllEvaluationData(String id) {
        return majorTeamAssessmentMapper.getAllEvaluationData(id);
    }

    /**
     * 获取全部的班子考核的领导班子民主测评表
     *
     * @param id
     * @return 根据班子考核表id获取领导班子民主测评表集合
     */
    @Override
    public List<MTALeaderBodyAppraisal> getAllDemocracyEvaluationByPId(String id) {
        return majorTeamAssessmentMapper.getAllDemocracyEvaluationByPId(id);
    }

    /**
     * 更新所有的发起班子考核的领导班子民主测评表
     *
     * @param sLeaderBodyAppraisalList 全部的发起班子考核的领导班子民主测评表
     */
    @Override
    public void updateAllSMTALeaderBodyAppraisal(List<SMTALeaderBodyAppraisal> sLeaderBodyAppraisalList) {
        majorTeamAssessmentMapper.updateAllSMTALeaderBodyAppraisal(sLeaderBodyAppraisalList);
    }

    /**
     * @param id
     * @return 获取全部的发起班子考核的领导人员及非领导职务人员民主测评表结果
     */
    @Override
    public List<SMTADemocraticAppraisal> getAllSMTADemocracyEvaluationData(String id) {
        return majorTeamAssessmentMapper.getAllSMTADemocracyEvaluationData(id);
    }

    /**
     * @param id
     * @return 获取全部的 班子考核的领导人员及非领导职务人员民主测评表结果
     */
    @Override
    public List<MTADemocraticAppraisal> getAllMTADemocracyEvaluationData(String id) {
        return majorTeamAssessmentMapper.getAllMTADemocracyEvaluationData(id);
    }

    /**
     * 更新所有的发起班子考核的 领导人员及非领导职务人员民主测评表结果
     *
     * @param smtaDemocraticAppraisalList 全部的发起班子考核的 领导人员及非领导职务人员民主测评表
     */
    @Override
    public void updateAllSMTADemocracyEvaluation(List<SMTADemocraticAppraisal> smtaDemocraticAppraisalList) {
        majorTeamAssessmentMapper.updateAllSMTADemocracyEvaluation(smtaDemocraticAppraisalList);
    }

    /**
     * @param id
     * @return 获取全部的 发起班子考核的 后备干部民主推荐表测评结果
     */
    @Override
    public List<SMTAReserveCadres> getAllSMTAReserveCadres(String id) {
        return majorTeamAssessmentMapper.getAllSMTAReserveCadres(id);
    }

    /**
     * @param id
     * @return 获取全部的 班子考核的 后备干部民主推荐表测评结果
     */
    @Override
    public List<MTAReserveCadres> getAllMTAReserveCadres(String id) {
        return majorTeamAssessmentMapper.getAllMTAReserveCadres(id);
    }

    /**
     * 批量插入发起班子考核的 后备干部民主推荐表测评结果
     *
     * @param smtaReserveCadresList
     */

    @Override
    public void insertAllReserveCadres(List<SMTAReserveCadres> smtaReserveCadresList) {
        majorTeamAssessmentMapper.insertAllReserveCadres(smtaReserveCadresList);
    }

    @Override
    public void clearAllReserveCadres(String id) {
        majorTeamAssessmentMapper.clearAllReserveCadres(id);
    }
}
