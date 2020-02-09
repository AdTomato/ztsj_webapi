package com.authine.cloudpivot.web.api.controller;

import com.alibaba.fastjson.JSON;
import com.authine.cloudpivot.engine.api.facade.BizObjectFacade;
import com.authine.cloudpivot.engine.api.facade.OrganizationFacade;
import com.authine.cloudpivot.engine.api.facade.WorkflowInstanceFacade;
import com.authine.cloudpivot.engine.api.model.organization.UserModel;
import com.authine.cloudpivot.engine.api.model.runtime.BizObjectModel;
import com.authine.cloudpivot.engine.enums.ErrCode;
import com.authine.cloudpivot.web.api.bean.*;
import com.authine.cloudpivot.web.api.controller.base.BaseController;
import com.authine.cloudpivot.web.api.service.IExternalTalentIntroducti;
import com.authine.cloudpivot.web.api.utils.DoubleUtils;
import com.authine.cloudpivot.web.api.utils.ExternalTalentIntroductiUtils;
import com.authine.cloudpivot.web.api.utils.Points;
import com.authine.cloudpivot.web.api.utils.UserUtils;
import com.authine.cloudpivot.web.api.view.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;

import java.util.*;

/**
 * @Author: wangyong
 * @Date: 2020-01-17 15:54
 * @Description: 紧缺人才外部引进招聘
 */
@RestController
@Slf4j
@RequestMapping("/ext/externalTalentIntroducti")
public class ExternalTalentIntroductiController extends BaseController {

    @Autowired
    IExternalTalentIntroducti externalTalentIntroducti;

    @PostMapping("/sendWf")
    public ResponseResult<String> sendExternalTalentIntroductiWf(@RequestBody SIntroduceEvaluation introduceEvaluation) {
        log.info("发起紧缺人才外部引进招聘面试评价流程");
        String userId = UserUtils.getUserId(getUserId());
        BizObjectFacade bizObjectFacade = getBizObjectFacade();
        OrganizationFacade organizationFacade = getOrganizationFacade();
        WorkflowInstanceFacade workflowInstanceFacade = getWorkflowInstanceFacade();
        if (null == introduceEvaluation) {
            return getErrResponseResult("参数不能为空", ErrCode.SYS_PARAMETER_EMPTY.getErrCode(), ErrCode.SYS_PARAMETER_EMPTY.getErrMsg());
        }
        List<BizObjectModel> models = new ArrayList<>();
        if (introduceEvaluation.getOrdinaryJudges() != null && 0 != introduceEvaluation.getOrdinaryJudges().size()) {
            for (User ordinaryJudge : introduceEvaluation.getOrdinaryJudges()) {
                models.add(getModel(ordinaryJudge, introduceEvaluation, "否"));
            }
        }

        if (null != introduceEvaluation.getProfessionalJudges() && 0 != introduceEvaluation.getProfessionalJudges().size()) {
            for (User professionalJudge : introduceEvaluation.getProfessionalJudges()) {
                models.add(getModel(professionalJudge, introduceEvaluation, "是"));
            }
        }
        UserModel user = organizationFacade.getUser(userId);
        List<String> ids = bizObjectFacade.addBizObjects(userId, models, "id");
        for (String id : ids) {
            workflowInstanceFacade.startWorkflowInstance(user.getDepartmentId(), user.getId(), "InterviewGrade", id, true);
        }

        createIntroduceEvaluationSum(userId, bizObjectFacade, introduceEvaluation);

        return getErrResponseResult("success", ErrCode.OK.getErrCode(), ErrCode.OK.getErrMsg());
    }

    @PutMapping("/calculate")
    public ResponseResult<String> calculateExternalTalentIntroductiWf(@RequestParam String userName) {
        synchronized (ExternalTalentIntroductiController.class) {
            // 汇总表
            IntroduceEvaluationSum introduceEvaluationSum = externalTalentIntroducti.getIntroduceEvaluationSumByUserName(userName);

            // 获取汇总表全部子表信息
            List<InterviewCondition> interviewConditionList = externalTalentIntroducti.getAllInterviewCondition(userName);
            Map<String, InterviewCondition> map = new HashMap();
            init(introduceEvaluationSum, interviewConditionList, map);

            // 有关这个人员的全部评价表主表信息
            List<IntroduceEvaluation> introduceEvaluationList = externalTalentIntroducti.getAllIntroduceEvaluationByUserName(userName);

            // 获取关于这个人员的全部评价表子表信息
            List<InterviewCondition> seInterviewConditionList = externalTalentIntroducti.getAllSEInterviewCondition(userName);

            Double score = 0D;
            Double expected_salary = 0D;
            Integer pNum = 0;
            for (IntroduceEvaluation introduceEvaluation :
                    introduceEvaluationList) {
                if (null != introduceEvaluation.getScore()) {
                    score += DoubleUtils.nullToDouble(introduceEvaluation.getScore());
                    pNum += 1;
                }

                if (null != introduceEvaluation.getExpectedPosition()) {
                    introduceEvaluationSum.setExpectedPosition(introduceEvaluation.getExpectedPosition());
                }

                if (null != introduceEvaluation.getExpectedSalary()) {
                    introduceEvaluationSum.setExpectedSalary(introduceEvaluation.getExpectedSalary());
                }

                switch (introduceEvaluation.getEvaluationResult()) {
                    case Points.EXCELLENT_POINT:
                        introduceEvaluationSum.setExcellentPoint(introduceEvaluationSum.getExcellentPoint() + 1);
                        break;
                    case Points.PREFERABLY_POINT:
                        introduceEvaluationSum.setPreferablyPoint(introduceEvaluationSum.getPreferablyPoint() + 1);
                        break;
                    case Points.ORDINARY_POINT:
                        introduceEvaluationSum.setOrdinaryPoint(introduceEvaluationSum.getOrdinaryPoint() + 1);
                        break;
                    case Points.POOL_POINT:
                        introduceEvaluationSum.setPoolPoint(introduceEvaluationSum.getPoolPoint() + 1);
                        break;
                }
                switch (introduceEvaluation.getOfficeAdvice()) {
                    case Points.HIRE_POINT:
                        introduceEvaluationSum.setHirePoint(introduceEvaluationSum.getHirePoint() + 1);
                        break;
                    case Points.NOT_ACCEPTED:
                        introduceEvaluationSum.setNotAccepted(introduceEvaluationSum.getNotAccepted() + 1);
                        break;
                }
                switch (introduceEvaluation.getRating()) {
                    case Points.POSITIVE_HEIGHT:
                        introduceEvaluationSum.setPositiveHeight(introduceEvaluationSum.getPositiveHeight() + 1);
                        break;
                    case Points.ADVANCED:
                        introduceEvaluationSum.setAdvanced(introduceEvaluationSum.getAdvanced() + 1);
                        break;
                    case Points.INTERMEDIATE:
                        introduceEvaluationSum.setIntermediate(introduceEvaluationSum.getIntermediate() + 1);
                        break;
                    case Points.ASSISTANT_MANAGER:
                        introduceEvaluationSum.setAssistantManager(introduceEvaluationSum.getAssistantManager() + 1);
                        break;
                }
                if (StringUtils.isEmpty(introduceEvaluation.getExpectedPosition())) {
                    introduceEvaluationSum.setExpectedPosition(introduceEvaluation.getExpectedPosition());
                }
                if (StringUtils.isEmpty(introduceEvaluation.getExpectedSalary() + "")) {
                    introduceEvaluationSum.setExpectedSalary(introduceEvaluation.getExpectedSalary());
                }

            }

            if (pNum != 0) {
                introduceEvaluationSum.setScore(DoubleUtils.doubleRound(score / pNum, 2));
            }

            Integer pt = 0;
            Integer zj = 0;
            for (InterviewCondition interviewCondition : seInterviewConditionList) {
                InterviewCondition i = map.get(interviewCondition.getGradeA());
                if (null != i) {
                    i.setScore(i.getScore() + interviewCondition.getScore());
                    if ("仪态仪表和语言表达".equals(i.getGradeA())) {
                        pt += 1;
                    } else if ("专业知识".equals(i.getGradeA())) {
                        zj += 1;
                    }
                }
            }

            if (pt == 0) {
                pt = 1;
            }
            if (zj == 0) {
                zj = 1;
            }

            Double totalScore = 0D;
            for (InterviewCondition interviewCondition : interviewConditionList) {
                switch (interviewCondition.getGradeA()) {
                    case "仪态仪表和语言表达":
                    case "企业认同及岗位认知度":
                    case "应聘动机和期望值":
                        interviewCondition.setScore(DoubleUtils.doubleRound(interviewCondition.getScore() / pt, 2));
                        totalScore += DoubleUtils.doubleRound(interviewCondition.getScore() * 0.5, 2);
                        break;
                    case "专业知识":
                        interviewCondition.setScore(DoubleUtils.doubleRound(interviewCondition.getScore() / zj, 2));
                        totalScore += DoubleUtils.doubleRound(interviewCondition.getScore() * 0.5, 2);
                        break;
                }
            }
            introduceEvaluationSum.setTotalPoints(totalScore);

            externalTalentIntroducti.updateIntroduceEvaluationSum(introduceEvaluationSum);
            externalTalentIntroducti.updateAllInterviewCondition(interviewConditionList);

        }
        return null;
    }

    private void init(IntroduceEvaluationSum introduceEvaluationSum, List<InterviewCondition> interviewConditionList, Map map) {
        introduceEvaluationSum.setScore(0d);
        introduceEvaluationSum.setExcellentPoint(0);
        introduceEvaluationSum.setPreferablyPoint(0);
        introduceEvaluationSum.setOrdinaryPoint(0);
        introduceEvaluationSum.setPoolPoint(0);
        introduceEvaluationSum.setHirePoint(0);
        introduceEvaluationSum.setNotAccepted(0);
        introduceEvaluationSum.setPositiveHeight(0);
        introduceEvaluationSum.setAdvanced(0);
        introduceEvaluationSum.setIntermediate(0);
        introduceEvaluationSum.setAssistantManager(0);
        introduceEvaluationSum.setExpectedPosition("");
        introduceEvaluationSum.setExpectedSalary(0d);
        for (InterviewCondition interviewCondition : interviewConditionList) {
            interviewCondition.setScore(0D);
            map.put(interviewCondition.getGradeA(), interviewCondition);
        }
    }

    /**
     * @param userId              : 创建数据的用户
     * @param bizObjectFacade     : 创建数据的引擎
     * @param introduceEvaluation :
     * @return : void
     * @Author: wangyong
     * @Date: 2020/1/17 17:40
     * @Description:
     */
    private void createIntroduceEvaluationSum(String userId, BizObjectFacade bizObjectFacade, SIntroduceEvaluation introduceEvaluation) {
        List<String> ids = externalTalentIntroducti.weatherHaveIntroduceEvaluationSumByUserName(introduceEvaluation.getUserName());
        if (ids.size() != 0) {
            return;
        }
        BizObjectModel model = new BizObjectModel();
        model.setSchemaCode("IntroduceEvaluationSum");
        model.setSequenceStatus(Points.COMPLETED);
        Map map = new HashMap();
        map.put("user_name", introduceEvaluation.getUserName());
        map.put("age", introduceEvaluation.getAge());
        map.put("evaluation_criterion", "具有行业优秀企业工作经历，在重难点项目关键岗位任职，曾解决过复杂技术难题或项目管理问题；工作经验比较丰富，取得了较好的工作业绩。优秀，得90-100分；较好，得80-89分；一般，得60-79分。");
        map.put("full_mark", 100);
        map.put("native_place", introduceEvaluation.getNativePlace());
        map.put("now_unit_and_duty", introduceEvaluation.getNowUnitAndDuty());
        map.put("recommend_company", introduceEvaluation.getRecommendCompany());
        map.put("score", 0);
        map.put("total_points", 0);
        map.put("excellent_point", 0);
        map.put("preferably_point", 0);
        map.put("ordinary_point", 0);
        map.put("pool_point", 0);
        map.put("hire_point", 0);
        map.put("not_accepted", 0);
        map.put("positive_height", 0);
        map.put("advanced", 0);
        map.put("intermediate", 0);
        map.put("assistant_manager", 0);
        map.put("expected_salary", 0);
        model.put(map);
        String id = bizObjectFacade.saveBizObject(userId, model, true);
        List<InterviewCondition> list = ExternalTalentIntroductiUtils.createInterviewCondition(id, introduceEvaluation.getUserName());
        externalTalentIntroducti.insertInterviewCondition(list);
        log.info("创建紧缺人才引进汇总表成功");
    }

    /**
     * @param user                : 参评人
     * @param introduceEvaluation : 设置信息
     * @param weatherExpert       : 是否专家
     * @return : com.authine.cloudpivot.engine.api.model.runtime.BizObjectModel
     * @Author: wangyong
     * @Date: 2020/1/17 16:12
     * @Description: 设置紧缺人才评价表model
     */
    private BizObjectModel getModel(User user, SIntroduceEvaluation introduceEvaluation, String weatherExpert) {
        BizObjectModel model = new BizObjectModel();
        model.setSchemaCode("IntroduceEvaluation");
        Map map = new HashMap();
        map.put("userName", introduceEvaluation.getUserName());
        map.put("age", introduceEvaluation.getAge());
        map.put("nativePlace", introduceEvaluation.getNativePlace());
        map.put("nowUnitAndDuty", introduceEvaluation.getNowUnitAndDuty());
        map.put("recommendCompany", introduceEvaluation.getRecommendCompany());
        map.put("judges", JSON.toJSONString(Arrays.asList(user)));
        map.put("weatherExpert", weatherExpert);
        model.put(map);
        return model;
    }

}
