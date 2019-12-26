package com.authine.cloudpivot.web.api.controller;

import com.authine.cloudpivot.web.api.bean.Appraisal;
import com.authine.cloudpivot.web.api.bean.DemocraticAppraisal;
import com.authine.cloudpivot.web.api.bean.StaffCongressEvaluation;
import com.authine.cloudpivot.web.api.service.IStaffCongressEvaluation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: wangyong
 * @Date: 2019-12-26 11:16
 * @Description: 职代会测评控制层
 */
@RestController
@RequestMapping("/ext/staffCongressEvaluation")
@Slf4j
public class StaffCongressEvaluationController {

    @Autowired
    IStaffCongressEvaluation staffCongressEvaluation;

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
    }

    /**
     * @Author: wangyong
     * @Date: 2019/12/26 11:32
     * @return : void
     * @Description: 计算班子成员民主测评表
     */
    private void calculateDemocraticAppraisal(List<String> idList, String id) {
        // 获取全部的发起职代会测评表中的班子成员民主测评表
        List<Appraisal> apList = staffCongressEvaluation.getAllAppraisalData(id);
        // 获取全部的职代会测评表中的班子成员民主测评表
        List<DemocraticAppraisal> daList = staffCongressEvaluation.getAllDemocraticAppraisalByPId(id);

        Map<String, Appraisal> apMap = new HashMap<>();
        for (Appraisal ap :
                apList) {
            apMap.put(ap.getLeadershipName(), ap);
        }

        for (DemocraticAppraisal da :
                daList) {
            if (idList.contains(da.getParentId())) {
                Appraisal ap = apMap.get(da.getLeadershipName());
                switch (da.getOption()) {
                    case "优秀": {
                        ap.setExcellentPoll(ap.getExcellentPoll() + 1);
                    } break;
                    case "称职": {
                        ap.setCompetentPoll(ap.getCompetentPoll() + 1);
                    } break;
                    case "基本称职": {
                        ap.setBasicCompetentPoll(ap.getBasicCompetentPoll() + 1);
                    } break;
                    case "不称职": {
                        ap.setNotCompetentPoll(ap.getNotCompetentPoll() + 1);
                    } break;
                }
            }
        }

        staffCongressEvaluation.updateAllAppraisalData(apList);
    }

}
