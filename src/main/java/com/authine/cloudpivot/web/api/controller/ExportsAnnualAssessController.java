package com.authine.cloudpivot.web.api.controller;

import com.authine.cloudpivot.engine.api.facade.BizObjectFacade;
import com.authine.cloudpivot.engine.api.model.runtime.BizObjectModel;
import com.authine.cloudpivot.engine.enums.ErrCode;
import com.authine.cloudpivot.web.api.bean.*;
import com.authine.cloudpivot.web.api.controller.base.BaseController;
import com.authine.cloudpivot.web.api.service.ExpertsAnnualAssService;
import com.authine.cloudpivot.web.api.view.ResponseResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/ext/exportsannual")
public class ExportsAnnualAssessController extends BaseController {
    private Logger log = LoggerFactory.getLogger(ExportsAnnualAssessController.class);

    @Autowired
    private ExpertsAnnualAssService expertsAnnualAssService;

    /**
     * 年度专家考核发起开会流程时,专家列表的自动填充
     *
     * @param expertsAssessInfo
     * @return
     */
    @RequestMapping("/exportslist")
    public ResponseResult<List> findexportslist(@RequestBody ExpertsAssessInfo expertsAssessInfo) {
        String userId = this.getUserId();
        List<ExpertsInfos> errorList = new ArrayList<>();
        if (expertsAssessInfo.getAnnual().length() == 0 || expertsAssessInfo.getAnnual() == null || expertsAssessInfo.getAssessment_type().length() == 0 || expertsAssessInfo.getAssessment_type() == null) {
            return this.getOkResponseResult(errorList, "error");
        }
        if (expertsAssessInfo.getAssessment_content().length() != 0 && expertsAssessInfo.getAssessment_content() != null) {
            if ("所在公司".equals(expertsAssessInfo.getAssessment_content()) && expertsAssessInfo.getWork_unit().length() != 0 && expertsAssessInfo.getWork_unit() != null) {
                List<ExpertsInfos> exportsList = expertsAnnualAssService.findExportsList(expertsAssessInfo);
                return this.getOkResponseResult(exportsList, "success");
            }
            if ("局评委会".equals(expertsAssessInfo.getAssessment_content())) {
                List<ExpertsInfos> exportsList = expertsAnnualAssService.findAllExportsList(expertsAssessInfo);
                return this.getOkResponseResult(exportsList, "success");
            }
            return this.getOkResponseResult(errorList, "error");

        }
        return this.getOkResponseResult(errorList, "error");


    }

    /**
     * 年度专家考核存储分数
     *
     * @param expertsAssessScore
     * @return
     */
    @RequestMapping("/savescore")
    public ResponseResult<String> savescore(@RequestBody ExpertsAssessScore expertsAssessScore) {
        if (expertsAssessScore.getAssessment_type().length() == 0 || expertsAssessScore.getAssessment_type() == null) {
            return this.getOkResponseResult("失败", "error");
        }

        if ("工程技术".equals(expertsAssessScore.getAssessment_type())) {
            // 创建数据的引擎类
            List<AnnualEngineerTechDetails> engineer_technology_annual = expertsAssessScore.getEngineer_technology_annual();

            List<BizObjectModel> models = new ArrayList<>();
            for (int i = 0; i < engineer_technology_annual.size(); i++) {
                BizObjectModel model = new BizObjectModel();
                model.setSchemaCode("expert_ann_score_detail");
                Map<String, Object> map = new HashMap<>();
                map.put("expert_ann_score", expertsAssessScore.getId());
                map.put("expert_ann_apply", expertsAssessScore.getEngineer_technology_annual().get(i).getU_id());
                map.put("row_id", expertsAssessScore.getEngineer_technology_annual().get(i).getId());
                map.put("review_discussion", expertsAssessScore.getEngineer_technology_annual().get(i).getReview_discussion());
                map.put("poser_field_service", expertsAssessScore.getEngineer_technology_annual().get(i).getPoser_field_service());
                map.put("train_teaching", expertsAssessScore.getEngineer_technology_annual().get(i).getTrain_teaching());
                map.put("technology_innovate", expertsAssessScore.getEngineer_technology_annual().get(i).getTechnology_innovate());
                map.put("engineer_tech_total", expertsAssessScore.getEngineer_technology_annual().get(i).getTotal_score());
                model.put(map);
                // 数据状态为生效状态
                model.setSequenceStatus("COMPLETED");
                models.add(model);
            }
            BizObjectFacade bizObjectFacade = super.getBizObjectFacade();
            String userId = "ff8080816e3e92fb016e3e9628ff00b4";
            log.info("当前操作用户id：" + userId);
            // 使用引擎方法批量创建数据
            bizObjectFacade.addBizObjects(userId, models, "id");
            // 清空专家年度考核的每个人的打分项
            expertsAnnualAssService.resetEngineerTechAnnual(expertsAssessScore.getId());

            return getOkResponseResult("success", ErrCode.OK.getErrMsg());
        }

        if ("财务审计".equals(expertsAssessScore.getAssessment_type())) {
            // 创建数据的引擎类
            List<AnnualFinancialDetails> financial_audit_annual = expertsAssessScore.getFinancial_audit_annual();

            List<BizObjectModel> models = new ArrayList<>();
            for (int i = 0; i < financial_audit_annual.size(); i++) {
                BizObjectModel model = new BizObjectModel();
                model.setSchemaCode("expert_ann_score_detail");
                Map<String, Object> map = new HashMap<>();
                map.put("expert_ann_score", expertsAssessScore.getId());
                map.put("expert_ann_apply", expertsAssessScore.getFinancial_audit_annual().get(i).getU_id());
                map.put("row_id", expertsAssessScore.getFinancial_audit_annual().get(i).getId());
                map.put("topic_research", expertsAssessScore.getFinancial_audit_annual().get(i).getTopic_research());
                map.put("work_achievement", expertsAssessScore.getFinancial_audit_annual().get(i).getWork_achievement());
                map.put("study_train", expertsAssessScore.getFinancial_audit_annual().get(i).getStudy_train());
                map.put("echange_teach", expertsAssessScore.getFinancial_audit_annual().get(i).getEchange_teach());
                map.put("finance_total", expertsAssessScore.getFinancial_audit_annual().get(i).getTotal_score());
                model.put(map);
                // 数据状态为生效状态
                model.setSequenceStatus("COMPLETED");
                models.add(model);
            }
            BizObjectFacade bizObjectFacade = super.getBizObjectFacade();
            String userId = "ff8080816e3e92fb016e3e9628ff00b4";
            log.info("当前操作用户id：" + userId);
            // 使用引擎方法批量创建数据
            bizObjectFacade.addBizObjects(userId, models, "id");
            // 清空专家年度考核的每个人的打分项
            expertsAnnualAssService.resetFinancialauditannual(expertsAssessScore.getId());

            return getOkResponseResult("success", ErrCode.OK.getErrMsg());
        }

        if ("工程经济".equals(expertsAssessScore.getAssessment_type())) {

            List<AnnualEngineerEconomicDetails> engineer_economy_annual = expertsAssessScore.getEngineer_economy_annual();


            List<BizObjectModel> models = new ArrayList<>();
            for (int i = 0; i < engineer_economy_annual.size(); i++) {
                BizObjectModel model = new BizObjectModel();
                model.setSchemaCode("expert_ann_score_detail");
                Map<String, Object> map = new HashMap<>();
                map.put("expert_ann_score", expertsAssessScore.getId());
                map.put("expert_ann_apply", expertsAssessScore.getEngineer_economy_annual().get(i).getU_id());
                map.put("row_id", expertsAssessScore.getEngineer_economy_annual().get(i).getId());
                map.put("solve_problems", expertsAssessScore.getEngineer_economy_annual().get(i).getSolve_problems());
                map.put("construction_promotion", expertsAssessScore.getEngineer_economy_annual().get(i).getConstruction_promotion());
                map.put("special_events", expertsAssessScore.getEngineer_economy_annual().get(i).getSpecial_events());
                map.put("culture_teaching", expertsAssessScore.getEngineer_economy_annual().get(i).getCulture_teaching());
                map.put("engineer_economic_total", expertsAssessScore.getEngineer_economy_annual().get(i).getTotal_score());
                model.put(map);
                // 数据状态为生效状态
                model.setSequenceStatus("COMPLETED");
                models.add(model);
            }
            BizObjectFacade bizObjectFacade = super.getBizObjectFacade();
            String userId = "ff8080816e3e92fb016e3e9628ff00b4";
            log.info("当前操作用户id：" + userId);
            // 使用引擎方法批量创建数据
            bizObjectFacade.addBizObjects(userId, models, "id");
            // 清空专家年度考核的每个人的打分项
            expertsAnnualAssService.resetEngineerEconomicAnnual(expertsAssessScore.getId());

            return getOkResponseResult("success", ErrCode.OK.getErrMsg());
        }

        return this.getOkResponseResult("失败", "error");

    }


    /**
     * 年度专家考核开会打分
     * 开发打分计算平均分并存储
     *
     * @param expertsAssessScore
     * @return
     */
    @RequestMapping("/countandsavescore")
    public ResponseResult<String> countandsavescore(@RequestBody ExpertsAssessScore expertsAssessScore) {
        if (expertsAssessScore.getAssessment_content().length() == 0 || expertsAssessScore.getAssessment_content() == null) {
            return this.getOkResponseResult("失败", "error");
        }
        if (expertsAssessScore.getAssessment_type().length() == 0 || expertsAssessScore.getAssessment_type() == null) {
            return this.getOkResponseResult("失败", "error");
        }

        if ("财务审计".equals(expertsAssessScore.getAssessment_type())) {
            String expert_ann_score = expertsAssessScore.getId();
            List<ExpertAnnualAvgScore> expertAnnualAvgScores = expertsAnnualAssService.countFinanceAvg(expert_ann_score);
            expertsAnnualAssService.updateFinanceAvg(expertAnnualAvgScores);
            if (expertsAssessScore.getAssessment_content().length() != 0 && expertsAssessScore.getAssessment_content() != null) {
                if ("所在公司".equals(expertsAssessScore.getAssessment_content())) {
                    expertsAnnualAssService.updateFinanceDeptJudgesScore(expertAnnualAvgScores);
                    return this.getOkResponseResult("成功", "success");
                }
                if ("局评委会".equals(expertsAssessScore.getAssessment_content())) {
                    expertsAnnualAssService.updateFinanceBureauJudgesScore(expertAnnualAvgScores);
                    return this.getOkResponseResult("成功", "success");
                }
                return this.getOkResponseResult("失败", "error");

            }
            return this.getOkResponseResult("失败", "error");


        }
        if ("工程技术".equals(expertsAssessScore.getAssessment_type())) {
            String expert_ann_score = expertsAssessScore.getId();
            List<ExpertAnnualAvgScore> expertAnnualAvgScores = expertsAnnualAssService.countTechAvg(expert_ann_score);
            expertsAnnualAssService.updateTechAvg(expertAnnualAvgScores);
            if (expertsAssessScore.getAssessment_content().length() != 0 && expertsAssessScore.getAssessment_content() != null) {
                if ("所在公司".equals(expertsAssessScore.getAssessment_content())) {
                    expertsAnnualAssService.updateTechDeptJudgesScore(expertAnnualAvgScores);
                    return this.getOkResponseResult("成功", "success");
                }
                if ("局评委会".equals(expertsAssessScore.getAssessment_content())) {
                    expertsAnnualAssService.updateTechBureauJudgesScore(expertAnnualAvgScores);
                    return this.getOkResponseResult("成功", "success");
                }
                return this.getOkResponseResult("失败", "error");

            }
            return this.getOkResponseResult("失败", "error");


        }
        if ("工程经济".equals(expertsAssessScore.getAssessment_type())) {
            String expert_ann_score = expertsAssessScore.getId();
            List<ExpertAnnualAvgScore> expertAnnualAvgScores = expertsAnnualAssService.countEconomicAvg(expert_ann_score);
            expertsAnnualAssService.updateEconomicAvg(expertAnnualAvgScores);
            if (expertsAssessScore.getAssessment_content().length() != 0 && expertsAssessScore.getAssessment_content() != null) {
                if ("所在公司".equals(expertsAssessScore.getAssessment_content())) {
                    expertsAnnualAssService.updateEconomicDeptJudgesScore(expertAnnualAvgScores);
                    return this.getOkResponseResult("成功", "success");
                }
                if ("局评委会".equals(expertsAssessScore.getAssessment_content())) {
                    expertsAnnualAssService.updateEconomicBureauJudgesScore(expertAnnualAvgScores);
                    return this.getOkResponseResult("成功", "success");
                }
                return this.getOkResponseResult("失败", "error");

            }
            return this.getOkResponseResult("失败", "error");


        }

        return this.getOkResponseResult("失败", "error");

    }

}