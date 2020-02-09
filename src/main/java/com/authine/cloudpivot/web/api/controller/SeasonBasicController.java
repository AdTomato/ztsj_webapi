package com.authine.cloudpivot.web.api.controller;

import com.authine.cloudpivot.engine.api.facade.BizObjectFacade;
import com.authine.cloudpivot.engine.api.model.runtime.BizObjectModel;
import com.authine.cloudpivot.web.api.bean.*;
import com.authine.cloudpivot.web.api.controller.base.BaseController;
import com.authine.cloudpivot.web.api.service.IAssessmentDetail;
import com.authine.cloudpivot.web.api.service.SeasonAssessService;
import com.authine.cloudpivot.web.api.view.ResponseResult;
import jodd.util.StringUtil;
import org.dom4j.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.*;

/**
 * 部门季度考核 基础部门15个人打分
 *
 * @author zsh
 * @since 2019-12-2
 */
@RestController
@RequestMapping("/ext/seasonbasic")
public class SeasonBasicController extends BaseController {
    private Logger log = LoggerFactory.getLogger(LeadpersonController.class);

    @Autowired
    private SeasonAssessService seasonAssessService;

    @Autowired
    IAssessmentDetail assessmentDetail;

    @RequestMapping("/savescore")
    public ResponseResult<String> savescore(@RequestBody SaveScoreSubmitVO saveScoreSubmit) {
        String userId = this.getUserId();
        String seasonassessmentId = saveScoreSubmit.getSeasonassessmentId();
        List<DeptEffect> deptEffectList = saveScoreSubmit.getDeptEffectList();
//        List<String> depteffectIds = new ArrayList<>();
        log.info("执行：checkRepeat");
        log.info("存储的数据为:" + saveScoreSubmit);
        int num = seasonAssessService.checkRepeat(seasonassessmentId, userId);
        if (num != 0) {
            return this.getOkResponseResult("error", "重复储存分数");
        }
        List<VoteInfo> voteInfos = new ArrayList<>();
        try {

            for (int i = 0; i < deptEffectList.size(); i++) {
                if (null == deptEffectList.get(i).getId() || "".equals(deptEffectList.get(i).getId())) {
                    continue;
                }
                VoteInfo voteInfo = new VoteInfo();
                voteInfo.setAssessmentSeason(saveScoreSubmit.getAssessmentSeason());
                voteInfo.setAssessmentYear(saveScoreSubmit.getAssessmentYear());
                voteInfo.setUserId(userId);
                voteInfo.setDeptNameId(deptEffectList.get(i).getDeptNameList().get(0).getId());
                voteInfo.setBasicScore(deptEffectList.get(i).getBasicScore());
                voteInfo.setHandleScore(deptEffectList.get(i).getHandleScore());
                voteInfo.setWorkareaDeduct(deptEffectList.get(i).getWorkareaDeduct());
                voteInfo.setOfficialcontentDeduct(deptEffectList.get(i).getOfficialcontentDeduct());
                voteInfo.setHandleDeduct(deptEffectList.get(i).getHandleDeduct());
                voteInfo.setTotalScore(deptEffectList.get(i).getTotalScore());
                voteInfo.setDeptEffectId(deptEffectList.get(i).getId());
                voteInfo.setParentId(seasonassessmentId);
//                depteffectIds.add(i, deptEffectList.get(i).getId());
                voteInfos.add(voteInfo);
//                seasonAssessService.saveScore(voteInfo);
            }
            seasonAssessService.saveScore(voteInfos);
            seasonAssessService.resetScore(seasonassessmentId);
            log.info("存储分数成功,当前存储分数的userId为:" + userId);
            return this.getOkResponseResult("success", "存储分数成功");

        } catch (Exception e) {
            log.error(e.getMessage());
            return this.getOkResponseResult("error", "存储分数出错");
        }

    }

    @RequestMapping("/countavg")
    public ResponseResult<String> countavg(@RequestParam(required = false) String id, String instanceid) {

        try {

            log.info("执行：countavg方法");
            log.info("参数id：" + id);
            List<AvgScore> results = seasonAssessService.countAvg(id);
            if (results.size() == 0) {
                return this.getOkResponseResult("error", "计算平均数出错");
            }

            log.info("删除基层打分全部已办：deleteWorkitemfinished");
            seasonAssessService.deleteWorkitemfinished(instanceid);

            for (int i = 0; i < results.size(); i++) {
                AvgScore avgScore = results.get(i);
                seasonAssessService.updateAvg(avgScore);
            }
            return this.getOkResponseResult("success", "计算和存储平均数成功");

        } catch (Exception e) {
            log.error(e.getMessage());
            return this.getOkResponseResult("error", "存储平均数出错");
        }
    }

    @RequestMapping("/counttotal")
    public ResponseResult<String> counttotal(@RequestParam(required = false) BigDecimal year, @RequestParam(required = false) String season, @RequestParam(required = false) String id) {
        String val = year + "";
        log.info("年度:" + year);
        log.info("季度:" + season);
        log.info("id:" + id);
        if ("".equals(val)) {
            return this.getOkResponseResult("error", "年份为空");
        }
        if ("".equals(season)) {
            return this.getOkResponseResult("error", "季度为空");
        }
        try {
            List<TotalScore> results = seasonAssessService.countTotal(id);
            String assessmentId = assessmentDetail.getAssessmentIdByAnnual(year + "年度");
            ;
            if (results.size() == 0 || null == results.get(0)) {
                return this.getOkResponseResult("error", "计算总分出错");
            }

            if (assessmentId == null) {
                log.info("创建年度考核得分汇总表");
                assessmentId = insertAssessmentDetail(year + "年度", getUserId());
            }

            for (int i = 0; i < results.size(); i++) {
                TotalScore totalScore = results.get(i);
                seasonAssessService.updateTotal(totalScore);
                if (assessmentId != null) {
                    AssessmentSummaryDetail assessmentSummaryDetail = assessmentDetail.getAssessmentDetailByParentIdAndDepartment(assessmentId, totalScore.getDepartment());
                    if (assessmentSummaryDetail == null) {
                        // 不存在，创建
                        log.info("创建年度考核得分汇总表明细");
                        insertAssessmentSummaryDetail(assessmentId, totalScore.getDepartment(), season, totalScore.getScore());
                    } else {
                        // 存在更新
                        log.info("更新年度考核得分汇总表明细");
                        updateAssessmentSummaryDetail(assessmentSummaryDetail, season, totalScore.getScore());
                    }
                }
            }
            return this.getOkResponseResult("success", "计算和存储总分成功");

        } catch (Exception e) {
            log.error(e.getMessage());
            return this.getOkResponseResult("error", "计算总分出错");
        }
    }

    /**
     * 创建年度考核得分汇总表
     *
     * @param annual 年度
     * @param userId 用户
     * @return 汇总表id
     */
    private String insertAssessmentDetail(String annual, String userId) {
        BizObjectFacade facade = super.getBizObjectFacade();
        BizObjectModel model = new BizObjectModel();
        model.setSchemaCode("assessment_summary");
        Map<String, Object> map = new HashMap<>();
        map.put("annual", annual);
        model.put(map);
        model.setSequenceStatus("COMPLETED");
        return facade.saveBizObjectModel(userId, model, "id");
    }

    /**
     * 创建机关部门年度考核得分明细
     *
     * @param assessmentId 部门id
     * @param season       季度
     * @param totalScore   分数
     */
    private void insertAssessmentSummaryDetail(String assessmentId, String department, String season, BigDecimal totalScore) {
        AssessmentSummaryDetail assessmentSummaryDetail = new AssessmentSummaryDetail();
        assessmentSummaryDetail.setId(UUID.randomUUID().toString().replaceAll("-", ""));
        assessmentSummaryDetail.setParentId(assessmentId);
        assessmentSummaryDetail.setDepartment(department);
        switch (season) {
            case "第一季度": {
                assessmentSummaryDetail.setFirstQuarter(Double.parseDouble(totalScore + ""));
                assessmentSummaryDetail.setSecondQuarter(0D);
                assessmentSummaryDetail.setThirdQuarter(0D);
                assessmentSummaryDetail.setFourQuarter(0D);
            }
            break;
            case "第二季度": {
                assessmentSummaryDetail.setFirstQuarter(0D);
                assessmentSummaryDetail.setSecondQuarter(Double.parseDouble(totalScore + ""));
                assessmentSummaryDetail.setThirdQuarter(0D);
                assessmentSummaryDetail.setFourQuarter(0D);
            }
            break;
            case "第三季度": {
                assessmentSummaryDetail.setFirstQuarter(0D);
                assessmentSummaryDetail.setSecondQuarter(0D);
                assessmentSummaryDetail.setThirdQuarter(Double.parseDouble(totalScore + ""));
                assessmentSummaryDetail.setFourQuarter(0D);
            }
            break;
            case "第四季度": {
                assessmentSummaryDetail.setFirstQuarter(0D);
                assessmentSummaryDetail.setSecondQuarter(0D);
                assessmentSummaryDetail.setThirdQuarter(0D);
                assessmentSummaryDetail.setFourQuarter(Double.parseDouble(totalScore + ""));
            }
            break;
        }
        assessmentSummaryDetail.setAnnualEvaluation(0D);
        assessmentSummaryDetail.setAnnualScore(Double.parseDouble(totalScore + "") / 4 * 0.5);
        log.info("创建的明细：" + assessmentSummaryDetail);
        assessmentDetail.insertAssessmentSummaryDetail(assessmentSummaryDetail);
    }

    /**
     * 更新年度机关考核得分汇总表明细
     *
     * @param assessmentSummaryDetail 机关部门考核得分明细表
     * @param season                  季度
     * @param totalScore              分数
     */
    private void updateAssessmentSummaryDetail(AssessmentSummaryDetail assessmentSummaryDetail, String season, BigDecimal totalScore) {
        Double score = 0D;
        switch (season) {
            case "第一季度":
                assessmentSummaryDetail.setFirstQuarter(Double.parseDouble(totalScore + ""));
                break;
            case "第二季度":
                assessmentSummaryDetail.setSecondQuarter(Double.parseDouble(totalScore + ""));
                break;
            case "第三季度":
                assessmentSummaryDetail.setThirdQuarter(Double.parseDouble(totalScore + ""));
                break;
            case "第四季度":
                assessmentSummaryDetail.setFourQuarter(Double.parseDouble(totalScore + ""));
                break;
        }
        Double seasonTotalScore = 0D;
        seasonTotalScore = (assessmentSummaryDetail.getFirstQuarter() +
                assessmentSummaryDetail.getSecondQuarter() +
                assessmentSummaryDetail.getThirdQuarter() +
                assessmentSummaryDetail.getFourQuarter()) / 4;
        assessmentSummaryDetail.setAnnualScore(seasonTotalScore * 0.5 + assessmentSummaryDetail.getAnnualEvaluation() * 0.5);
        assessmentDetail.updateAssessmentDetailById(assessmentSummaryDetail);
    }

}
