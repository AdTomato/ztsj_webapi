package com.authine.cloudpivot.web.api.controller;

import com.authine.cloudpivot.web.api.bean.*;
import com.authine.cloudpivot.web.api.controller.base.BaseController;
import com.authine.cloudpivot.web.api.service.ICreateAssessmentResult;
import com.authine.cloudpivot.web.api.service.IStaffCongressEvaluation;
import lombok.extern.slf4j.Slf4j;
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
     * @param idList :
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
                sac.setEvaluationStores(sac.getEvaluationStores() + ac.getEvaluationStores());
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

}
