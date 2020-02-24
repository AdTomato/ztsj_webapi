package com.authine.cloudpivot.web.api.controller;

import com.authine.cloudpivot.web.api.bean.*;
import com.authine.cloudpivot.web.api.controller.base.BaseController;
import com.authine.cloudpivot.web.api.service.ICreateAssessmentResult;
import com.authine.cloudpivot.web.api.service.MajorTeamAssessmentService;
import com.authine.cloudpivot.web.api.utils.Points;
import com.authine.cloudpivot.web.api.utils.UserUtils;
import com.authine.cloudpivot.web.api.view.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * @Author:lfh
 * @Date: 2020/1/1 11:35
 * @Description： 班子考核控制层
 */
@RestController
@RequestMapping("/ext/majorTeamAssessment")
@Slf4j
public class MajorTeamAssessmentController extends BaseController{

    @Autowired
    MajorTeamAssessmentService majorTeamAssessmentService;

    @Autowired
    ICreateAssessmentResult createAssessmentResult;

    @RequestMapping("/calculateResoult")
    public ResponseResult<Void> calculateResult(@RequestParam("id") String id) {
        log.info("开始执行班子考核测评方法");
        log.info("当前传入的id值为："+id);
        //获取发起班子考核表中主表的所有信息
        MajorTeamAssessment mta = majorTeamAssessmentService.getMajorTeamAssessmentInfo(id);
        Map map = new HashMap();
        map.put("id",id );
        //参会人数
        map.put("max",mta.getParticipantsPeoples());
        map.put("createdTime",mta.getCreatedTime());
        //根据unit获取从0到最大投票人数的班子考核表的id
        List<String> idList = majorTeamAssessmentService.getMajorTeamIdByUnit(map);
        log.info("获取的是0-"+mta.getParticipantsPeoples()+"的班子考核表信息");
        log.info("获取的id列表是："+idList);

        //计算领导班子民主测评表
        calculateLeaderDemocracyEvaluation(idList,id);

        //计算领导人员及非领导职务人员民主测评表
        calculateDemocracyEvaluation(idList,id);

        //计算公司后备干部民主推荐表
        calculateReserveCadres(idList,id);

        //更新发起班子考核主表的信息
        Map<String,Object>info = new HashMap<>();
        info.put("votoPeoples",idList.size());
        info.put("id",id );
        majorTeamAssessmentService.updatemajorTeamAssessmentInfo(info);
        log.info("班子考核测评投票结果计算完毕");
        return getOkResponseResult("计算完毕");
    }


    /**
     *计算公司后备干部民主推荐表
     * @param idList
     * @param id
     */
    private void calculateReserveCadres(List<String> idList, String id) {
        log.info("开始计算公司后备干部民主推荐表");
        //创建一个集合用于存后备干部投票结果
        List<SMTAReserveCadres> smtaReserveCadresList = new ArrayList<>();
        //获取全部的班子考核的 公司后备干部民主推荐表
        List<MTAReserveCadres> mtaReserveCadresList = majorTeamAssessmentService.getAllMTAReserveCadres(id);

        Map<String,SMTAReserveCadres> srcMap = new HashMap<>();
        if (mtaReserveCadresList !=null && !mtaReserveCadresList.isEmpty()) {
            //计算每人的票数
            for (MTAReserveCadres rc : mtaReserveCadresList) {
                //有效参会人数
                if (idList.contains(rc.getParentId())) {
                    //该人还没被投过，进行初始化
                    if (srcMap.get(rc.getRecommendPosition() + rc.getReferralName() + rc.getReferralNowPosition()) == null) {
                        SMTAReserveCadres src = new SMTAReserveCadres();
                        src.setId(UUID.randomUUID().toString().replace("-", ""));
                        src.setParentId(rc.getPId());
                        src.setReferralName(rc.getReferralName());
                        src.setReferralNowPosition(rc.getReferralNowPosition());
                        src.setRecommendPosition(rc.getRecommendPosition());
                        src.setReferralPoll(1);
                        srcMap.put(rc.getRecommendPosition() + rc.getReferralName() + rc.getReferralNowPosition(), src);
                    } else {
                        //被投过，修改票数
                        SMTAReserveCadres src = srcMap.get(rc.getRecommendPosition() + rc.getReferralName() + rc.getReferralNowPosition());
                        src.setReferralPoll(src.getReferralPoll() + 1);
                    }
                }
            }
            //将修改票数存入结果集
            for (SMTAReserveCadres src : srcMap.values()) {
                smtaReserveCadresList.add(src);

            }

            // 清空子表
            majorTeamAssessmentService.clearAllReserveCadres(id);

            //进行批量插入
            majorTeamAssessmentService.insertAllReserveCadres(smtaReserveCadresList);
            log.info("计算公司后备干部民主推荐表完成");
        }else {
            log.info("无推荐人员");
        }
    }


    /**
     *计算领导人员及非领导职务人员民主测评表
     * @param idList  班子考核表id
     * @param id 发起班子考核表的id
     */
    private void calculateDemocracyEvaluation(List<String> idList, String id) {
        log.info("开始计算领导人员及非领导职务人员民主测评表");
        //获取全部的发起班子考核的领导人员及非领导职务人员民主测评表
        List<SMTADemocraticAppraisal> smtaDemocraticAppraisalList = majorTeamAssessmentService.getAllSMTADemocracyEvaluationData(id);

        //获取全部的班子考核的领导人员及非领导职务人员民主测评表
        List<MTADemocraticAppraisal> mtaDemocraticAppraisalList = majorTeamAssessmentService.getAllMTADemocracyEvaluationData(id);

        Map<String,SMTADemocraticAppraisal> sdaMAp = new HashMap();

        if (smtaDemocraticAppraisalList != null && !smtaDemocraticAppraisalList.isEmpty()) {
            //将发起班子考核中的领导人员及非领导职务人员民主测评表的“name”存入hashmap中
            for (SMTADemocraticAppraisal sda : smtaDemocraticAppraisalList) {
                sda.setExcellentPoll(0);
                sda.setCompetentPoll(0);
                sda.setBasicCompetentPoll(0);
                sda.setNotCompetentPoll(0);
                sdaMAp.put(sda.getLeadershipName(), sda);
            }

            //计算每个人员的票数
            for (MTADemocraticAppraisal da : mtaDemocraticAppraisalList) {
                //有效参会人数
                if (idList.contains(da.getParentId())) {
                    SMTADemocraticAppraisal sda = sdaMAp.get(da.getLeadershipName());
                    if (null != sda) {
                        switch (da.getOption()) {
                            case "优秀":
                                sda.setExcellentPoll(sda.getExcellentPoll() + 1);
                                break;
                            case "称职":
                                sda.setCompetentPoll(sda.getCompetentPoll() + 1);
                                break;
                            case "基本称职":
                                sda.setBasicCompetentPoll(sda.getBasicCompetentPoll() + 1);
                                break;
                            case "不称职":
                                sda.setNotCompetentPoll(sda.getNotCompetentPoll() + 1);
                                break;
                        }
                    }
                }
            }
            // 创建领导人员考核明细
            List<AssessmentResult> carList = new ArrayList<>();
            List<AssessmentResult> uarList = new ArrayList<>();
            String arId = null;
            Date time = new Date();
            StringBuilder sb = new StringBuilder();
            for (SMTADemocraticAppraisal sda :
                    smtaDemocraticAppraisalList) {
                AssessmentResult ar = new AssessmentResult();
                ar.setPId(id);
                ar.setAssessTime(time);
//            ar.setTime(time.getYear() + "-" + time.getMonth() + "-" + time.getDay());
                ar.setLeadershipPerson(sda.getLeadershipName());
                ar.setAssessContent("班子大考核-领导人员及非领导职务人员民主测评");
                sb.delete(0, sb.length());
                sb.append("优秀票数：");
                sb.append(sda.getExcellentPoll() == null ? 0 : sda.getExcellentPoll() + "\n");
                sb.append("称职票数：");
                sb.append(sda.getCompetentPoll() == null ? 0 : sda.getCompetentPoll() + "\n");
                sb.append("基本称职票数：");
                sb.append(sda.getBasicCompetentPoll() == null ? 0 : sda.getBasicCompetentPoll() + "\n");
                sb.append("不称职票数：");
                sb.append(sda.getNotCompetentPoll() == null ? 0 : sda.getNotCompetentPoll() + "\n");
                ar.setAssessResult(sb.toString());

                arId = createAssessmentResult.isHaveAssessmentResult(ar);
                if (null != arId) {
                    ar.setId(arId);
                    uarList.add(ar);
                } else {
                    carList.add(ar);
                }
            }

            String userId = UserUtils.getUserId(getUserId());

            if (0 != carList.size()) {
                log.info("新增考核结果");
                createAssessmentResult.createAssessmentResults(this.getBizObjectFacade(), userId, carList);
            }

            if (0 != uarList.size()) {
                log.info("更新考核结果");
                createAssessmentResult.updateAssessmentResult(uarList);
            }

            //更新最终结果
            majorTeamAssessmentService.updateAllSMTADemocracyEvaluation(smtaDemocraticAppraisalList);
            log.info("计算领导人员及非领导职务人员民主测评表完成");
        }else{
            log.info("领导人员及非领导职务人员民主测评表无测评人员");
        }
    }

    /**
     * 计算领导班子民主测评表
     * @param idList
     * @param id
     */
    private void calculateLeaderDemocracyEvaluation(List<String> idList, String id) {
        log.info("开始计算领导班子民主测评表");
        //获取全部的发起班子考核的领导班子民主测评表
        List<SMTALeaderBodyAppraisal> sLeaderBodyAppraisalList = majorTeamAssessmentService.getAllEvaluationData(id);

        //获取全部的班子考核的领导班子民主测评表
        List<MTALeaderBodyAppraisal> leaderBodyAppraisalList =majorTeamAssessmentService.getAllDemocracyEvaluationByPId(id);

        Map<String,SMTALeaderBodyAppraisal> slbaMap = new HashMap<>();
        //将发起班子考核中领导班子民主评测表中测评项目存入HashMap中
        for (SMTALeaderBodyAppraisal slba : sLeaderBodyAppraisalList) {
            slba.setGoodPoll(0);
            slba.setPreferablyPoll(0);
            slba.setOrdinaryPoll(0);
            slba.setBadPoll(0);
            slbaMap.put(slba.getAssessmentProject(),slba);
        }

        //计算每个测评项目的票数
        for (MTALeaderBodyAppraisal lba : leaderBodyAppraisalList) {
            //有效的参会人数 idlist中的max
            if (idList.contains(lba.getParentId())){
                SMTALeaderBodyAppraisal slba = slbaMap.get(lba.getAssessmentProject());
                if (null != slba){
                    switch (lba.getEvaluationOpinions()){
                        case Points.GOOD_POINT:
                            slba.setGoodPoll(slba.getGoodPoll() + 1);
                            break;
                        case Points.PREFERABLY_POINT:
                            slba.setPreferablyPoll(slba.getPreferablyPoll() + 1);
                            break;
                        case Points.ORDINARY_POINT:
                            slba.setOrdinaryPoll(slba.getOrdinaryPoll() +1);
                            break;
                        case Points.POOL_POINT:
                            slba.setBadPoll(slba.getBadPoll() +1);
                            break;
                    }
                }
            }
        }
        //更新最终结果
        majorTeamAssessmentService.updateAllSMTALeaderBodyAppraisal(sLeaderBodyAppraisalList);
        log.info("计算领导班子民主测评成功");

    }


}
