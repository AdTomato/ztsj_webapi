package com.authine.cloudpivot.web.api.mapper;

import com.authine.cloudpivot.web.api.bean.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @Author:lfh
 * @Date: 2020/1/1 11:43
 * @Description：
 */
@Mapper
public interface MajorTeamAssessmentMapper {

    /**
     * 获取发起班子考核表的信息
     *
     * @param id
     * @return 班子考核表信息
     */
    MajorTeamAssessment getMajorTeamAssessmentInfo(String id);

    /**
     * 根据unit获取从0到最大投票人数的班子考核表的id
     *
     * @param map
     * @return List<String>班子考核表中的id
     */
    List<String> getMajorTeamIdByUnit(Map map);

    /**
     * 获取全部的发起班子考核的领导班子民主测评表
     *
     * @param id 父id
     * @return 根据班子考核表id获取领导班子民主测评表集合
     */
    List<SMTALeaderBodyAppraisal> getAllEvaluationData(String id);

    /**
     * 获取全部的班子考核的领导班子民主测评表
     *
     * @param id
     * @return 根据班子考核表id获取领导班子民主测评表集合
     */
    List<MTALeaderBodyAppraisal> getAllDemocracyEvaluationByPId(String id);

    /**
     * 更新所有的发起班子考核的领导班子民主测评表
     *
     * @param sLeaderBodyAppraisalList 全部的发起班子考核的领导班子民主测评表
     */
    void updateAllSMTALeaderBodyAppraisal(List<SMTALeaderBodyAppraisal> sLeaderBodyAppraisalList);

    /**
     * 获取全部 发起班子考核的 领导人员及非领导职务人员民主测评表结果
     *
     * @param id
     * @return 根据班子考核表的id获取全部领导人员及非领导职务人员民主测评表结果
     */
    List<SMTADemocraticAppraisal> getAllSMTADemocracyEvaluationData(String id);


    /**
     * 获取全部 班子考核的 领导人员及非领导职务人员民主测评表结果
     *
     * @param id
     * @return 根据班子考核表的id获取全部领导人员及非领导职务人员民主测评表结果
     */
    List<MTADemocraticAppraisal> getAllMTADemocracyEvaluationData(String id);

    /**
     * 更新所有的发起班子考核的 领导人员及非领导职务人员民主测评表结果
     *
     * @param smtaDemocraticAppraisalList 全部的发起班子考核的 领导人员及非领导职务人员民主测评表
     */
    void updateAllSMTADemocracyEvaluation(List<SMTADemocraticAppraisal> smtaDemocraticAppraisalList);

    /**
     * 获取全部的 发起班子考核的 后备干部民主推荐表
     *
     * @param id
     * @return 根据班子考核表的id 获取全部的 后备干部民主推荐表结果
     */
    List<SMTAReserveCadres> getAllSMTAReserveCadres(String id);

    /**
     * 获取全部的 发起班子考核的 后备干部民主推荐表
     *
     * @param id
     * @return 根据班子考核表的id 获取全部的 后备干部民主推荐表结果
     */
    List<MTAReserveCadres> getAllMTAReserveCadres(String id);

    /**
     * 批量插入 后备干部民主推荐表结果
     *
     * @param smtaReserveCadresList
     */
    void insertAllReserveCadres(List<SMTAReserveCadres> smtaReserveCadresList);

    /**
     * 更新发起班子考核主表的信息
     *
     * @param info
     */
    void updatemajorTeamAssessmentInfo(Map<String, Object> info);

    void clearAllReserveCadres(String id);


}
