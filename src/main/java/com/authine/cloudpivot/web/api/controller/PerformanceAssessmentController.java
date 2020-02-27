package com.authine.cloudpivot.web.api.controller;

import com.alibaba.fastjson.JSON;
import com.authine.cloudpivot.engine.api.facade.OrganizationFacade;
import com.authine.cloudpivot.engine.api.facade.WorkflowInstanceFacade;
import com.authine.cloudpivot.engine.api.model.organization.DepartmentModel;
import com.authine.cloudpivot.engine.api.model.organization.UserModel;
import com.authine.cloudpivot.engine.enums.ErrCode;
import com.authine.cloudpivot.web.api.bean.*;
import com.authine.cloudpivot.web.api.constants.Constants;
import com.authine.cloudpivot.web.api.controller.base.BaseController;
import com.authine.cloudpivot.web.api.dto.PerformanceAssessmentDto;
import com.authine.cloudpivot.web.api.parameter.InsertPerformanceAssessmentDetail;
import com.authine.cloudpivot.web.api.parameter.SendPerformanceAssessmentWf;
import com.authine.cloudpivot.web.api.service.ICreateAssessmentResult;
import com.authine.cloudpivot.web.api.service.PerformanceAssessmentService;
import com.authine.cloudpivot.web.api.utils.DoubleUtils;
import com.authine.cloudpivot.web.api.utils.SystemDataSetUtils;
import com.authine.cloudpivot.web.api.utils.UserUtils;
import com.authine.cloudpivot.web.api.view.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;

import java.util.*;

/**
 * @Author: wangyong
 * @Date: 2020-02-26 00:52
 * @Description: 履职考核controller
 */
@RestController
@RequestMapping("/ext/performanceAssessment")
@Slf4j
public class PerformanceAssessmentController extends BaseController {


    @Autowired
    PerformanceAssessmentService performanceAssessmentService;

    @Autowired
    ICreateAssessmentResult createAssessmentResult;

    @PostMapping("/sendPerformanceAssessmentWf")
    public ResponseResult<Object> sendPerformanceAssessmentWf(@RequestBody SendPerformanceAssessmentWf sendPerformanceAssessmentWf) {
        log.info("--------------------------");
        log.info("创建履职考核流程");
        String assessmentContent = sendPerformanceAssessmentWf.getAssessmentContent();
        String annual = sendPerformanceAssessmentWf.getAnnual();
        Date date = sendPerformanceAssessmentWf.getDate();
        User unit = sendPerformanceAssessmentWf.getUnit();
        String id = sendPerformanceAssessmentWf.getId();
        List<SpaSurveyContent> spaSurveyContents = sendPerformanceAssessmentWf.getSpaSurveyContents();
        if (StringUtils.isEmpty(assessmentContent) || StringUtils.isEmpty(id) || StringUtils.isEmpty(annual) || null == date || null == unit || null == spaSurveyContents || 0 == spaSurveyContents.size()) {
            // 所有参数必填
            return this.getErrResponseResult(null, 404L, "存在必填项没有填写");
        }

        List<SpaAssessmentPeople> spaAssessmentPeoples = performanceAssessmentService.getSpaAssessmentPeoples(sendPerformanceAssessmentWf.getId());


        String userId = UserUtils.getUserId(this.getUserId());
        OrganizationFacade organizationFacade = this.getOrganizationFacade();
        WorkflowInstanceFacade workflowInstanceFacade = this.getWorkflowInstanceFacade();
        UserModel user = organizationFacade.getUser(userId);
        DepartmentModel department = organizationFacade.getDepartment(user.getDepartmentId());
        int insertNum = 0;
        List<String> objectIds = new ArrayList<>();
        List<PerformanceAssessment> performanceAssessments = new ArrayList<>();
        List<PaContent> paContents = new ArrayList<>();
        for (SpaAssessmentPeople spaAssessmentPeople : spaAssessmentPeoples) {
            PerformanceAssessment performanceAssessment = new PerformanceAssessment();
            performanceAssessment.setAnnual(annual);
            performanceAssessment.setDate(date);
            performanceAssessment.setTotalScore(0D);
            performanceAssessment.setResult("");
            performanceAssessment.setAssessmentContent(assessmentContent);
            performanceAssessment.setUnit(JSON.toJSONString(Arrays.asList(unit)));
            performanceAssessment.setJudges(JSON.toJSONString(sendPerformanceAssessmentWf.getJudges()));
            performanceAssessment.setUserName(spaAssessmentPeople.getUserName());
            performanceAssessment.setPosition(spaAssessmentPeople.getPosition());
            performanceAssessment.setSpaAssessmentPeopleId(spaAssessmentPeople.getId());
            SystemDataSetUtils.dataSet(Constants.PROCESSING_STATUS, user, department, performanceAssessment.getAssessmentContent(), performanceAssessment);
            objectIds.add(performanceAssessment.getId());
            for (SpaSurveyContent spaSurveyContent : spaSurveyContents) {
                PaContent paContent = new PaContent();
                paContent.setId(UUID.randomUUID().toString().replace("-", ""));
                paContent.setSortKey(spaSurveyContent.getSortKey());
                paContent.setParentId(performanceAssessment.getId());
                paContent.setSurveyContent(spaSurveyContent.getSurveyContent());
                paContents.add(paContent);
            }
            performanceAssessments.add(performanceAssessment);
            insertNum++;
            if (insertNum == 100) {
                // 插入数据
                insertNum = 0;
                performanceAssessmentService.insertPerformanceAssessments(performanceAssessments);
                performanceAssessmentService.insertPaContents(paContents);
                performanceAssessments.clear();
                paContents.clear();
            }
        }

        if (0 != insertNum) {
            // 插入数据
            performanceAssessmentService.insertPerformanceAssessments(performanceAssessments);
            performanceAssessmentService.insertPaContents(paContents);
            performanceAssessments.clear();
            paContents.clear();
        }

        for (String objectId : objectIds) {
            // 开启流程
            workflowInstanceFacade.startWorkflowInstance(user.getDepartmentId(), userId, Constants.PERFORMANCE_ASSESSMENT_WF, objectId, true);
        }
        log.info("履职考核数据生成完毕");
        return this.getErrResponseResult(null, ErrCode.OK.getErrCode(), ErrCode.OK.getErrMsg());
    }

    @PostMapping("/insertPerformanceAssessmentDetail")
    public ResponseResult<Object> insertPerformanceAssessmentDetail(@RequestBody InsertPerformanceAssessmentDetail insertPerformanceAssessmentDetail) {
        log.info("--------------------------");
        log.info("插入履职考核明细");
        String id = insertPerformanceAssessmentDetail.getId();
        List<PaContent> paContents = insertPerformanceAssessmentDetail.getPaContents();
        if (StringUtils.isEmpty(id) || null == paContents || 0 == paContents.size()) {
            // 参数存在空值
            this.getErrResponseResult(null, 404L, "存在空值");
        }

        String userId = UserUtils.getUserId(this.getUserId());
        UserModel user = this.getOrganizationFacade().getUser(userId);
        DepartmentModel department = this.getOrganizationFacade().getDepartment(user.getDepartmentId());

        List<PerformanceAssessmentDet> performanceAssessmentDets = new ArrayList<>();
        for (PaContent paContent : paContents) {
            PerformanceAssessmentDet performanceAssessmentDet = new PerformanceAssessmentDet();
            performanceAssessmentDet.setPerformanceAssessment(id);
            performanceAssessmentDet.setPaContentId(paContent.getId());
            performanceAssessmentDet.setSurveyContent(paContent.getSurveyContent());
            performanceAssessmentDet.setEvaluationScore(DoubleUtils.stringToDouble(paContent.getEvaluationScore()));
            SystemDataSetUtils.dataSet(Constants.COMPLETED_STATUS, user, department, performanceAssessmentDet.getPerformanceAssessment(), performanceAssessmentDet);
            performanceAssessmentDets.add(performanceAssessmentDet);
        }
        log.info("批量导入履职考核明细");
        performanceAssessmentService.insertPerformanceAssessmentDets(performanceAssessmentDets);
        log.info("插入履职考核明细完成");
        return this.getErrResponseResult(null, ErrCode.OK.getErrCode(), ErrCode.OK.getErrMsg());
    }

    @PutMapping("/calculation")
    public ResponseResult<Object> calculation(@RequestParam(required = true) String id) {
        log.info("--------------------------");
        log.info("计算履职考核：" + id);
        String userId = UserUtils.getUserId(this.getUserId());
        PerformanceAssessmentDto performanceAssessmentDto = performanceAssessmentService.getPerformanceAssessmentDto(id);
        if (!Constants.COMPLETED_STATUS.equals(performanceAssessmentDto.getSequenceStatus())) {
            // 流程尚未结束，无需计算
            log.info("流程尚未结束，无需计算");
            return this.getErrResponseResult(null, ErrCode.OK.getErrCode(), ErrCode.OK.getErrMsg());
        }
        if (null == performanceAssessmentDto || null == performanceAssessmentDto.getPaContents() || 0 == performanceAssessmentDto.getPaContents().size()) {
            return this.getErrResponseResult(null, 404L, "异常（获取的考核表为空）");
        }

        List<PaContent> paContents = performanceAssessmentDto.getPaContents();
        List<PerformanceAssessmentDet> performanceAssessmentDets = performanceAssessmentService.getPerformanceAssessmentDets(id);

        if (null == performanceAssessmentDets || 0 == performanceAssessmentDets.size()) {
            return this.getErrResponseResult(null, 404L, "异常（没有获取明细）");
        }

        Map<String, PaContent> data = new HashMap<>();
        for (PaContent paContent : paContents) {
            paContent.setFinalScore(0D);
            data.put(paContent.getId(), paContent);
        }
        performanceAssessmentDto.setTotalScore(0D);
        int judges = performanceAssessmentDets.size() / paContents.size();
        for (PerformanceAssessmentDet performanceAssessmentDet : performanceAssessmentDets) {
            PaContent paContent = data.get(performanceAssessmentDet.getPaContentId());
            if (null != paContent) {
                paContent.setFinalScore(DoubleUtils.nullToDouble(paContent.getFinalScore()) + performanceAssessmentDet.getEvaluationScore());
                performanceAssessmentDto.setTotalScore(DoubleUtils.nullToDouble(performanceAssessmentDto.getTotalScore()) + DoubleUtils.nullToDouble(performanceAssessmentDet.getEvaluationScore()));
            }
        }

        for (PaContent paContent : paContents) {
            paContent.setFinalScore(DoubleUtils.doubleRound(paContent.getFinalScore() / judges, 2));
        }
        performanceAssessmentDto.setTotalScore(DoubleUtils.doubleRound(performanceAssessmentDto.getTotalScore() / judges, 2));
        setResult(performanceAssessmentDto);
        log.info("更新履职考核主表");
        performanceAssessmentService.updatePerformanceAssessment(performanceAssessmentDto);
        log.info("更新履职考核子表");
        performanceAssessmentService.updatePaContent(paContents);
        log.info("更新发起履职考核表");
        performanceAssessmentService.updateSpaAssessmentPeople(performanceAssessmentDto);

        AssessmentResult assessmentResult = new AssessmentResult();
        assessmentResult.setLeadershipPerson(performanceAssessmentDto.getUserName());
        assessmentResult.setAssessContent(performanceAssessmentDto.getAssessmentContent());
        assessmentResult.setAssessTime(performanceAssessmentDto.getCreatedTime());
        assessmentResult.setAssessResult("整体得分：" + performanceAssessmentDto.getTotalScore() + "\n\n" +
                "整体评价意见：" + performanceAssessmentDto.getResult());
        assessmentResult.setPId(performanceAssessmentDto.getId());
        log.info("创建领导人员考核结果");
        createAssessmentResult.createAssessmentResult(this.getBizObjectFacade(), userId, assessmentResult);

        log.info("履职考核计算完毕");
        return this.getErrResponseResult(null, ErrCode.OK.getErrCode(), ErrCode.OK.getErrMsg());
    }

    private void setResult(PerformanceAssessmentDto performanceAssessmentDto) {
        Double totalScore = performanceAssessmentDto.getTotalScore();
        if (totalScore >= 70) {
            performanceAssessmentDto.setResult("继续任职");
        } else if (totalScore >= 60) {
            performanceAssessmentDto.setResult("需要诫勉");
        } else {
            performanceAssessmentDto.setResult("需要调整");
        }
    }

}
