package com.authine.cloudpivot.web.api.service;

import com.authine.cloudpivot.web.api.bean.*;

import java.util.List;
import java.util.Map;

/**
 * @Author:lfh
 * @Date: 2020/1/1 11:40
 * @Description：班子考核service接口
 */

public interface MajorTeamAssessmentService {

    /**
     * 根据id获取发起班子考核表中主表的所有信息
     *
     * @param id
     * @return
     */
    MajorTeamAssessment getMajorTeamAssessmentInfo(String id);

    /**
     * //根据unit获取从0到最大投票人数的班子考核表的id
     *
     * @param map
     * @return List<String>班子考核表中的id
     */
    List<String> getMajorTeamIdByUnit(Map map);

    /**
     * 更新发起班子考核主表的信息
     *
     * @param info
     */
    void updatemajorTeamAssessmentInfo(Map<String, Object> info);

    /**
     * 获取全部的发起班子考核的领导班子民主测评表
     *
     * @param id
     * @return
     */
    List<SMTALeaderBodyAppraisal> getAllEvaluationData(String id);

    /**
     * 获取全部的班子考核的领导班子民主测评表
     *
     * @param id
     * @return
     */
    List<MTALeaderBodyAppraisal> getAllDemocracyEvaluationByPId(String id);

    /**
     * 更新所有的发起班子考核的领导班子民主测评表
     *
     * @param sLeaderBodyAppraisalList 全部的发起班子考核的领导班子民主测评表
     */
    void updateAllSMTALeaderBodyAppraisal(List<SMTALeaderBodyAppraisal> sLeaderBodyAppraisalList);

    /**
     * 获取全部的发起班子考核的领导人员及非领导职务人员民主测评表
     *
     * @param id
     * @return
     */
    List<SMTADemocraticAppraisal> getAllSMTADemocracyEvaluationData(String id);

    /**
     * 获取全部的班子考核的领导人员及非领导职务人员民主测评表
     *
     * @param id
     * @return
     */
    List<MTADemocraticAppraisal> getAllMTADemocracyEvaluationData(String id);

    /**
     * 更新领导人员及非领导职务人员民主测评表结果
     *
     * @param smtaDemocraticAppraisalList
     */
    void updateAllSMTADemocracyEvaluation(List<SMTADemocraticAppraisal> smtaDemocraticAppraisalList);

    /**
     * 获取全部的发起班子考核 后备干部民主推荐表
     *
     * @param id
     * @return
     */
    List<SMTAReserveCadres> getAllSMTAReserveCadres(String id);

    /**
     * 获取全部的 班子考核的 后备干部民主推荐表
     *
     * @param id
     * @return
     */
    List<MTAReserveCadres> getAllMTAReserveCadres(String id);

    /**
     * 批量插入 后备干部民主推荐表结果
     *
     * @param smtaReserveCadresList
     */

    void insertAllReserveCadres(List<SMTAReserveCadres> smtaReserveCadresList);

    void clearAllReserveCadres(String id);
}
