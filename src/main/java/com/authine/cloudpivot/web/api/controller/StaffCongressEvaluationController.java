package com.authine.cloudpivot.web.api.controller;

import com.authine.cloudpivot.web.api.bean.*;
import com.authine.cloudpivot.web.api.controller.base.BaseController;
import com.authine.cloudpivot.web.api.service.ICreateAssessmentResult;
import com.authine.cloudpivot.web.api.service.IStaffCongressEvaluation;
import com.authine.cloudpivot.web.api.utils.Points;
import lombok.extern.slf4j.Slf4j;
import org.redisson.misc.Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.DecimalFormat;
import java.util.*;

/**
 * @Author: wangyong
 * @Date: 2019-12-26 11:16
 * @Description: 职代会测评控制层
 */
@RestController
@RequestMapping("/ext/staffCongressEvaluation")
@Slf4j
public class StaffCongressEvaluationController extends BaseController {

    @Autowired
    IStaffCongressEvaluation staffCongressEvaluation;

    @Autowired
    ICreateAssessmentResult createAssessmentResult;

    @RequestMapping("/calculateResoult")
    public void calculateResult(String id) {
        log.info("开始执行职代会测评计算方法");
        log.info("当前传入的id值为:" + id);
        StaffCongressEvaluation sce = staffCongressEvaluation.getStaffCongressEvaluationInfo(id);
        Map map = new HashMap();
        map.put("id", id);
        map.put("max", sce.getParticipantsPeoples());
        List<String> idList = staffCongressEvaluation.getAssessmentCongressData(map);
        log.info("获取的是从0到" + sce.getParticipantsPeoples() + "的职代会测评表信息");
        log.info("获取的id列表为:" + idList);
        // 计算班子成员民主测评表
        calculateDemocraticAppraisal(idList, id);

        // 计算“四好”领导班子评价表
        calculateLeadBodyEvaluate(idList, id);

        // 计算领导班子民主测评表
        calculateLeaderBodyAppraisal(idList, id);

        // 计算党风廉政建设情况测评表
        calculateHonestEvaluationForm(idList, id);
    }

    /**
     * @return : void
     * @Author: wangyong
     * @Date: 2019/12/26 11:32
     * @Description: 计算班子成员民主测评表
     */
    private void calculateDemocraticAppraisal(List<String> idList, String id) {
        // 获取全部的发起职代会测评表中的班子成员民主测评表
        List<Appraisal> apList = staffCongressEvaluation.getAllAppraisalData(id);
        // 获取全部的职代会测评表中的班子成员民主测评表
        List<DemocraticAppraisal> daList = staffCongressEvaluation.getAllDemocraticAppraisalByPId(id);

        Map<String, Appraisal> apMap = new HashMap<>();

        // 将发起职代会测评表中的班子成员民主测评表以领导人员的id值存储在hash map中
        for (Appraisal ap :
                apList) {
            ap.setExcellentPoll(0);
            ap.setCompetentPoll(0);
            ap.setBasicCompetentPoll(0);
            ap.setNotCompetentPoll(0);
            apMap.put(ap.getLeadershipName(), ap);
        }

        // 计算每个领导人员的票数
        for (DemocraticAppraisal da :
                daList) {
            if (idList.contains(da.getParentId())) {
                Appraisal ap = apMap.get(da.getLeadershipName());
                if (null != ap) {
                    switch (da.getOption()) {
                        case "优秀":
                            ap.setExcellentPoll(ap.getExcellentPoll() + 1);
                            break;
                        case "称职":
                            ap.setCompetentPoll(ap.getCompetentPoll() + 1);
                            break;
                        case "基本称职":
                            ap.setBasicCompetentPoll(ap.getBasicCompetentPoll() + 1);
                            break;
                        case "不称职":
                            ap.setNotCompetentPoll(ap.getNotCompetentPoll() + 1);
                            break;
                    }
                }

            }
        }

        List<AssessmentResult> arList = new ArrayList<>();
        String msg = null;
        for (Appraisal ap :
                apMap.values()) {
            AssessmentResult ar = new AssessmentResult();
            ar.setAssessTime(new Date());
            ar.setLeadershipPerson(ap.getLeadershipName());
            ar.setAssessContent("职代会测评-班子成员民主测评");
            msg = "";
            msg += "优秀票数:";
            msg += ap.getExcellentPoll() == null ? 0 : ap.getExcellentPoll() + "\n";
            msg += "称职票数:";
            msg += ap.getCompetentPoll() == null ? 0 : ap.getCompetentPoll() + "\n";
            msg += "基本称职票数:";
            msg += ap.getBasicCompetentPoll() == null ? 0 : ap.getBasicCompetentPoll() + "\n";
            msg += "不称职票数:";
            msg += ap.getNotCompetentPoll() == null ? 0 : ap.getNotCompetentPoll() + "\n";
            ar.setAssessResult(msg);
            arList.add(ar);
//            staffCongressEvaluation.updateAppraisalData(ap);
        }

        // 将每个领导人员的考核结果存储在考核结果表中
        String userId = this.getUserId();
        if (userId == null) {
            userId = "2c9280a26706a73a016706a93ccf002b";
        }
        // 将每个领导人员的考核结果存储在考核结果表中
        createAssessmentResult.createAssessmentResults(this.getBizObjectFacade(), userId, arList);
        // 更新最终的结果
        staffCongressEvaluation.updateAllAppraisalData(apList);
    }

    /**
     * @param idList : 从0到最大参选人数的id集合
     * @param id     : 发起职代会测评表的id
     * @return : void
     * @Author: wangyong
     * @Date: 2019/12/27 14:48
     * @Description: 计算“四好”领导班子评价表
     */
    private void calculateLeadBodyEvaluate(List<String> idList, String id) {
        // 获取所有的发起职代会测评表中的“四好”领导班子评价表
        List<SACLeadBodyEvaluate> sacList = staffCongressEvaluation.getAllSACLeadBodyEvaluateData(id);

        // 获取所有的职代会测评表中的“四好”领导班子评价表
        List<ACLeadBodyEvaluate> acList = staffCongressEvaluation.getAllACLeadBodyEvaluateData(id);

        Map<String, SACLeadBodyEvaluate> sacMap = new HashMap<>();

        for (SACLeadBodyEvaluate sac :
                sacList) {
            sac.setEvaluationStores(0D);
            sacMap.put(sac.getReviewKeyPoints(), sac);
        }

        // 计算总分
        for (ACLeadBodyEvaluate ac :
                acList) {
            if (idList.contains(ac.getParentId())) {
                SACLeadBodyEvaluate sac = sacMap.get(ac.getReviewKeyPoints());
                if (null != sac) {
                    sac.setEvaluationStores(sac.getEvaluationStores() + ac.getEvaluationStores());
                }
            }
        }

        DecimalFormat df = new DecimalFormat("#.##");

        // 计算平均分
        for (SACLeadBodyEvaluate sac :
                sacMap.values()) {
            Double result = sac.getEvaluationStores() / idList.size();
            sac.setEvaluationStores(Double.parseDouble(df.format(result)));
            log.info(sac.getReviewKeyPoints() + " 分数: " + result);
        }

        staffCongressEvaluation.updateAllSACLeadBodyEvaluateData(sacList);
        log.info("计算“四好”领导班子评价表成功");
    }

    /**
     * @Author: wangyong
     * @Date: 2019/12/28 15:33
     * @param idList : 从0到最大参选人数的id集合
     * @param id : 发起职代会测评表的id
     * @return : void
     * @Description: 计算领导班子民主测评表
     */
    private void calculateLeaderBodyAppraisal(List<String> idList, String id) {
        // 获取所有的发起职代会测评表中的领导班子民主测评表
        List<SACLeaderBodyAppraisal> sacList = staffCongressEvaluation.getAllSACLeaderBodyAppraisalData(id);

        // 获取所有的职代会测评表中的领导班子民主测评表
        List<ACLeaderBodyAppraisal> acList = staffCongressEvaluation.getAllACLeaderBodyAppraisalData(id);

        Map<String, SACLeaderBodyAppraisal> sacMap = new HashMap<>();

        for (SACLeaderBodyAppraisal sac :
                sacList) {
            sac.setGoodPoll(0);
            sac.setOrdinaryPoll(0);
            sac.setPreferablyPoll(0);
            sac.setBadPoll(0);
            sacMap.put(sac.getAssessmentProject(), sac);
        }

        for (ACLeaderBodyAppraisal ac :
                acList) {
            if (idList.contains(ac.getParentId())) {
                SACLeaderBodyAppraisal sac = sacMap.get(ac.getAssessmentProject());
                if (null != sac) {
                    switch (ac.getEvaluationOpinions()) {
                        case Points.GOOD_POINT: sac.setGoodPoll(sac.getGoodPoll() + 1);break;
                        case Points.PREFERABLY_POINT: sac.setPreferablyPoll(sac.getPreferablyPoll() + 1);break;
                        case Points.ORDINARY_POINT: sac.setOrdinaryPoll(sac.getOrdinaryPoll() + 1);break;
                        case Points.POOL_POINT: sac.setBadPoll(sac.getBadPoll() + 1);break;
                    }
                }
            }
        }

        staffCongressEvaluation.updateAllSACLeaderBodyAppraisalData(sacList);
        log.info("计算领导班子民主测评成功");
    }

    /**
     * @Author: wangyong
     * @Date: 2019/12/28 17:25
     * @param idList : 从0到最大参选人数的id集合
     * @param id : 发起职代会测评表的id
     * @return : void
     * @Description: 计算党风廉政建设情况测评表
     */
    private void calculateHonestEvaluationForm(List<String> idList, String id) {

        // 后去所有的发起职代会测评表中的党风廉政建设情况测评表
        List<ASCHonestEvaluationForm> ascList = staffCongressEvaluation.getAllASCHonestEvaluationFormData(id);

        // 获取所有的职代会测评表中的党风廉政建设情况测评表
        List<ACHonestEvaluationForm> acList = staffCongressEvaluation.getAllACHonestEvaluationFormData(id);

        Map<String, ASCHonestEvaluationForm> ascMap = new HashMap<>();

        for (ASCHonestEvaluationForm asc :
                ascList) {
            asc.setBadPoll(0);
            asc.setGoodPoll(0);
            asc.setOrdinaryPoll(0);
            asc.setPreferablyPoll(0);
            asc.setWaiverPoll(0);
            ascMap.put(asc.getReviewContent(), asc);
        }

        for (ACHonestEvaluationForm ac :
                acList) {
            if (idList.contains(ac.getParentId())) {
                ASCHonestEvaluationForm asc = ascMap.get(ac.getReviewContent());
                if (null != asc) {
                    switch (ac.getReviewOpinion()) {
                        case Points.GOOD_POINT: asc.setGoodPoll(asc.getGoodPoll() + 1); break;
                        case Points.PREFERABLY_POINT: asc.setPreferablyPoll(asc.getPreferablyPoll() + 1); break;
                        case Points.ORDINARY_POINT: asc.setOrdinaryPoll(asc.getOrdinaryPoll() + 1); break;
                        case Points.BAD_POINT: asc.setBadPoll(asc.getBadPoll() + 1); break;
                        case Points.WAIVER_POINT: asc.setWaiverPoll(asc.getWaiverPoll() + 1); break;
                    }
                }
            }
        }
        staffCongressEvaluation.updateAllASCHonestEvaluationFormData(ascList);
        log.info("计算党风廉政建设情况成功");
    }

}
