package com.authine.cloudpivot.web.api.controller;

import com.authine.cloudpivot.engine.enums.ErrCode;
import com.authine.cloudpivot.web.api.bean.DemocraticEvaluation;
import com.authine.cloudpivot.web.api.bean.SDemocraticEvaluation;
import com.authine.cloudpivot.web.api.controller.base.BaseController;
import com.authine.cloudpivot.web.api.service.IDemocraticEvaluation;
import com.authine.cloudpivot.web.api.utils.Points;
import com.authine.cloudpivot.web.api.view.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: wangyong
 * @Date: 2020-01-05 09:33
 * @Description: 民主评议表controller类
 */
@RestController
@RequestMapping("/ext/democraticEvaluation")
@Slf4j
public class DemocraticEvaluationController extends BaseController {

    @Autowired
    IDemocraticEvaluation democraticEvaluation;

    /**
     * @Author: wangyong
     * @Date: 2020/1/5 15:18
     * @param id : 发起民主评议表的id值
     * @return : com.authine.cloudpivot.web.api.view.ResponseResult<java.lang.Void>
     * @Description: 计算民主评议表结果
     */
    @GetMapping("/calculateResult")
    public ResponseResult<Void> calculateResult(String id) {
        log.info("开始计算民主评议表结果");
        log.info("当前计算的民主评议表的id为:" + id);
        if ("".equals(id)) {
            return getErrResponseResult(ErrCode.SYS_PARAMETER_EMPTY.getErrCode(), ErrCode.SYS_PARAMETER_EMPTY.getErrMsg());
        }
        SDemocraticEvaluation sd = democraticEvaluation.getSDemocraticEvaluationDataById(id);
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("max", sd.getParticipantsPeoples());
        List<DemocraticEvaluation> dList = democraticEvaluation.getAllDemocraticEvaluationData(map);

        // 设初值
        init(sd);

        if (null != dList && 0 != dList.size()) {
            for (DemocraticEvaluation d :
                    dList) {
                switch (d.getGeneralEvaluation()) {
                    case Points.SATISFIED_POINT: sd.setGSatisfiedPoll(sd.getGSatisfiedPoll() + 1); break;
                    case Points.BASIC_SATISFIED_POINT: sd.setGBasicSatisfiedPoll(sd.getGBasicSatisfiedPoll() + 1); break;
                    case Points.NO_SATISFIED_POINT: sd.setGNoSatisfiedPoll(sd.getGNoSatisfiedPoll() + 1); break;
                    case Points.NO_UNDERSTAND_POINT: sd.setGNoUnderstandPoll(sd.getGNoUnderstandPoll() + 1); break;
                }

                switch (d.getRegulationLawsOpinion()) {
                    case Points.SATISFIED_POINT: sd.setRSatisfiedPoll(sd.getRSatisfiedPoll() + 1); break;
                    case Points.BASIC_SATISFIED_POINT: sd.setRBasicSatisfiedPoll(sd.getRBasicSatisfiedPoll() + 1); break;
                    case Points.NO_SATISFIED_POINT: sd.setRNoSatisfiedPoll(sd.getRNoSatisfiedPoll() + 1); break;
                    case Points.NO_UNDERSTAND_POINT: sd.setRNoUnderstandPoll(sd.getRNoUnderstandPoll() + 1); break;
                }

                switch (d.getBadPractiveOpinion()) {
                    case Points.SATISFIED_POINT: sd.setBSatisfiedPoll(sd.getBSatisfiedPoll() + 1); break;
                    case Points.BASIC_SATISFIED_POINT: sd.setBBasicSatisfiedPoll(sd.getBBasicSatisfiedPoll() + 1); break;
                    case Points.NO_SATISFIED_POINT: sd.setBNoSatisfiedPoll(sd.getBNoSatisfiedPoll() + 1); break;
                    case Points.NO_UNDERSTAND_POINT: sd.setBNoUnderstandPoll(sd.getBNoUnderstandPoll() + 1); break;
                }

                switch (d.getInstitutionalReformOpinion()) {
                    case Points.SATISFIED_POINT: sd.setISatisfiedPoll(sd.getISatisfiedPoll() + 1); break;
                    case Points.BASIC_SATISFIED_POINT: sd.setIBasicSatisfiedPoll(sd.getIBasicSatisfiedPoll() + 1); break;
                    case Points.NO_SATISFIED_POINT: sd.setINoSatisfiedPoll(sd.getINoSatisfiedPoll() + 1); break;
                    case Points.NO_UNDERSTAND_POINT: sd.setINoUnderstandPoll(sd.getINoUnderstandPoll() + 1); break;
                }

                if (d.getProminentProblem().contains(Points.NO_STRICT_POINT)) {
                    sd.setNoStrictPoll(sd.getNoStrictPoll() + 1);
                }
                if (d.getProminentProblem().contains(Points.APPOINT_PEOPLE_POINT)) {
                    sd.setAppointPeoplePoll(sd.getAppointPeoplePoll() + 1);
                }
                if (d.getProminentProblem().contains(Points.INDIVIDUAL_POINT)) {
                    sd.setIndividualPoll(sd.getIndividualPoll() + 1);
                }
                if (d.getProminentProblem().contains(Points.RUN_OFFICE_POINT)) {
                    sd.setRunOfficePoll(sd.getRunOfficePoll() + 1);
                }
                if (d.getProminentProblem().contains(Points.BUY_SELL_OFFICE_POINT)) {
                    sd.setBuySellOfficePoll(sd.getBuySellOfficePoll() + 1);
                }
                if (d.getProminentProblem().contains(Points.CANVASSING_POINT)) {
                    sd.setCanvassingPoll(sd.getCanvassingPoll() + 1);
                }
                if (d.getProminentProblem().contains(Points.NO_OUTSTANDING_PROBLEMS_POINT)) {
                    sd.setNoOutstandingProblemsPoll(sd.getNoOutstandingProblemsPoll() + 1);
                }
                if (!"".equals(d.getConcreteContent())) {
                    if ("".equals(sd.getOtherProblems())) {
                        sd.setOtherProblems(d.getConcreteContent());
                    } else {
                        sd.setOtherProblems(sd.getOtherProblems() + "\n\n" + d.getConcreteContent());
                    }
                }
                if (!"".equals(d.getCommentSuggestion())) {
                    if ("".equals(sd.getCommentSuggestion())) {
                        sd.setCommentSuggestion(d.getCommentSuggestion());
                    } else {
                        sd.setCommentSuggestion(sd.getCommentSuggestion() + "\n\n" + d.getCommentSuggestion());
                    }
                }
            }
        }
        sd.setVotePeoples(dList.size());
        sd.setIsOrNotCloseVote("是");
        democraticEvaluation.updateSDemocraticEvaluation(sd);
        log.info("民主评议表结果计算成功");
        return getErrResponseResult(ErrCode.OK.getErrCode(), ErrCode.OK.getErrMsg());
    }

    /**
     * @Author: wangyong
     * @Date: 2020/1/5 13:59
     * @param sd : 发起民主评议表
     * @return : void
     * @Description: 初始化发起民主评议表
     */
    private void init(SDemocraticEvaluation sd) {

        sd.setGBasicSatisfiedPoll(0);
        sd.setGNoSatisfiedPoll(0);
        sd.setGNoUnderstandPoll(0);
        sd.setGSatisfiedPoll(0);

        sd.setRBasicSatisfiedPoll(0);
        sd.setRNoSatisfiedPoll(0);
        sd.setRNoUnderstandPoll(0);
        sd.setRSatisfiedPoll(0);

        sd.setBSatisfiedPoll(0);
        sd.setBNoUnderstandPoll(0);
        sd.setBBasicSatisfiedPoll(0);
        sd.setBNoSatisfiedPoll(0);

        sd.setIBasicSatisfiedPoll(0);
        sd.setINoSatisfiedPoll(0);
        sd.setINoUnderstandPoll(0);
        sd.setISatisfiedPoll(0);

        sd.setNoStrictPoll(0);
        sd.setAppointPeoplePoll(0);
        sd.setIndividualPoll(0);
        sd.setRunOfficePoll(0);
        sd.setBuySellOfficePoll(0);
        sd.setCanvassingPoll(0);
        sd.setNoOutstandingProblemsPoll(0);
        sd.setNoOutstandingProblemsPoll(0);
        sd.setOtherProblems("");
        sd.setCommentSuggestion("");
    }

}
