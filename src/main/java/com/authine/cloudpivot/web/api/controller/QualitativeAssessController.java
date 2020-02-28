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
import com.authine.cloudpivot.web.api.service.IControlGroupAssessment;
import com.authine.cloudpivot.web.api.service.IQualitativeAssess;
import com.authine.cloudpivot.web.api.utils.CreateQualitativeAssessContentUtils;
import com.authine.cloudpivot.web.api.utils.Points;
import com.authine.cloudpivot.web.api.utils.SequenceStatusUtils;
import com.authine.cloudpivot.web.api.utils.UserUtils;
import com.authine.cloudpivot.web.api.view.ResponseResult;
import jodd.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * @Author: wangyong
 * @Date: 2020-01-09 23:19
 * @Description: 定性考核Controller层
 */
@RestController
@RequestMapping("/ext/qualitativeAssessController")
@Slf4j
public class QualitativeAssessController extends BaseController {

    @Autowired
    IQualitativeAssess iQualitativeAssess;

    @Autowired
    IControlGroupAssessment iControlGroupAssessment;

    private static final String QUALITATIVE_ASSESS = "qualitative_assess";
    public static final String QUALITATIVE_ASSESS_DETAIL = "qualitative_assess_detail";

    @PostMapping("/sendQualitativeAssess")
    public ResponseResult<Void> sendQualitativeAssess(@RequestBody SendQualitativeAssess qualitativeAssess) {

        log.info("开始创建定性考核流程");
        BizObjectFacade bizObjectFacade = getBizObjectFacade();
        OrganizationFacade organizationFacade = getOrganizationFacade();
        WorkflowInstanceFacade workflowInstanceFacade = getWorkflowInstanceFacade();
        String userId = getUserId();
        if (StringUtil.isEmpty(userId)) {
            userId = Points.ADMIN_ID;
        }

        if (null == qualitativeAssess) {
            // 参数为空
            return getErrResponseResult(ErrCode.SYS_PARAMETER_EMPTY.getErrCode(), ErrCode.SYS_PARAMETER_EMPTY.getErrMsg());
        }
        List<BizObjectModel> models = new ArrayList<>();
        for (User user :
                qualitativeAssess.getAssessee()) {
            BizObjectModel model = new BizObjectModel();
//            model.setSequenceStatus(Points.COMPLETED);
            model.setSchemaCode(QUALITATIVE_ASSESS);
            Map map = new HashMap();
            map.put("assessee", JSON.toJSONString(Arrays.asList(user)));
            map.put("annual", qualitativeAssess.getAnnual());
            map.put("assessment_content", qualitativeAssess.getAssessmentContent());
            map.put("judge", JSON.toJSONString(qualitativeAssess.getJudge()));
            model.put(map);
            models.add(model);
        }
//        bizObjectFacad
        List<String> ids = bizObjectFacade.addBizObjects(userId, models, "id");
        List<QualitativeAssessContent> qualitativeAssessContentList = new ArrayList<>();
        if (0 != ids.size()) {
            for (String id :
                    ids) {
                qualitativeAssessContentList.addAll(CreateQualitativeAssessContentUtils.create(id));
            }

            iQualitativeAssess.insertQualitativeAssessContent(qualitativeAssessContentList);

            // 开启流程
            UserModel user = organizationFacade.getUser(userId);
            for (String id :
                    ids) {
                workflowInstanceFacade.startWorkflowInstance(user.getDepartmentId(), userId, "qualitative_assess_wf", id, true);
            }
        }
        log.info("创建定量考核流程成功");
        return getErrResponseResult(ErrCode.OK.getErrCode(), ErrCode.OK.getErrMsg());
    }

    @PostMapping("/insertQualitativeAssessDetail")
    public ResponseResult<Void> insertQualitativeAssessDetail(@RequestBody SaveQualitativeAssessDetail saveQualitativeAssessDetail) {

        log.info("开始创建定量考核明细");
        String userId = UserUtils.getUserId(getUserId());
        BizObjectFacade bizObjectFacade = getBizObjectFacade();

        if (null == saveQualitativeAssessDetail) {
            return getErrResponseResult(ErrCode.SYS_PARAMETER_EMPTY.getErrCode(), ErrCode.SYS_PARAMETER_EMPTY.getErrMsg());
        }

        List<QualitativeAssessDetail> qualitativeAssessDetailList = saveQualitativeAssessDetail.getQualitativeAssessDetails();
        List<BizObjectModel> models = new ArrayList<>();
        for (QualitativeAssessDetail qualitativeAssessDetail :
                qualitativeAssessDetailList) {
            BizObjectModel model = new BizObjectModel();
            model.setSchemaCode(QUALITATIVE_ASSESS_DETAIL);
            model.setSequenceStatus(Points.COMPLETED);
            Map map = new HashMap();
            map.put("qualitative_assess", saveQualitativeAssessDetail.getId());
            map.put("evaluation_content", qualitativeAssessDetail.getEvaluationContent());
            map.put("sheet_id", qualitativeAssessDetail.getSheetId());
            map.put("score", qualitativeAssessDetail.getScore());
            model.put(map);
            models.add(model);
        }
        bizObjectFacade.addBizObjects(userId, models, "id");
        log.info("创建定性考核明细成功");
        return getErrResponseResult(ErrCode.OK.getErrCode(), ErrCode.OK.getErrMsg());
    }

    @PutMapping("/calculateQualitativeAssess")
    public ResponseResult<Void> calculateQualitativeAssess(@RequestParam String id, @RequestParam Integer num) {

        iQualitativeAssess.cleanQualitativeAssessContent(id);
        log.info("清空考核明细成功");

        log.info("开始计算定性考核");
        if (null == id) {
            return getErrResponseResult(ErrCode.SYS_PARAMETER_EMPTY.getErrCode(), ErrCode.SYS_PARAMETER_EMPTY.getErrMsg());
        }
//        synchronized (QualitativeAssessController.class) {
//            List<String> peoples = iQualitativeAssess.getQualitativeAssessDetails(id);
//            log.info("评委人数：" + num + "；当前提交人数：" + peoples.size());
//            if (peoples.size() != num) {
//                log.info("流程尚未结束，无需计算");
//                return getErrResponseResult(ErrCode.PERMISSION_MANAGER_TYPE_ERR.getErrCode(), "流程尚未结束，无需计算");  // 参数为空
//            }
//        }
        List<String> peoples = iQualitativeAssess.getQualitativeAssessDetails(id);
        log.info("评委人数：" + num + "；当前提交人数：" + peoples.size());
        if (peoples.size() != num) {
            log.info("流程尚未结束，无需计算");
            return getErrResponseResult(ErrCode.PERMISSION_MANAGER_TYPE_ERR.getErrCode(), "流程尚未结束，无需计算");  // 参数为空
        }


        String userId = getUserId();
        if (StringUtil.isEmpty(userId)) {
            userId = Points.ADMIN_ID;
        }
        BizObjectFacade bizObjectFacade = getBizObjectFacade();
        QualitativeAssess qualitativeAssess = iQualitativeAssess.getqualitativeAssessById(id);
        List<QualitativeAssessContent> qualitativeAssessContentList = iQualitativeAssess.getQualitativeAssessContentById(id);
//        String assessmentContent = iQualitativeAssess.getAssessmentContent(id);
        String assessmentContent = qualitativeAssess.getAssessmentContent();
        Double finalScore = 0D;
        for (QualitativeAssessContent qualitativeAssessContent :
                qualitativeAssessContentList) {
            Double result = iQualitativeAssess.getQualitativeAssessContentFinalScore(qualitativeAssessContent.getId());
            finalScore += result;
            qualitativeAssessContent.setFinalScore(result);
        }

        iQualitativeAssess.updateQualitativeAssessContentFinalScore(qualitativeAssessContentList);
        ControlGroupDetail controlGroupDetail = new ControlGroupDetail();
        controlGroupDetail.setAssessee(qualitativeAssess.getAssessee());
        controlGroupDetail.setQuantitativeScore(0D);
        switch (assessmentContent) {
            case Points.CONTENT1_SCORE:
                controlGroupDetail.setContent1Score(finalScore);
                controlGroupDetail.setContent2Score(0D);
                controlGroupDetail.setContent3Score(0D);
                controlGroupDetail.setContent4Score(0D);
                break;
            case Points.CONTENT2_SCORE:
                controlGroupDetail.setContent1Score(0D);
                controlGroupDetail.setContent2Score(finalScore);
                controlGroupDetail.setContent3Score(0D);
                controlGroupDetail.setContent4Score(0D);
                break;
            case Points.CONTENT3_SCORE:
                controlGroupDetail.setContent1Score(0D);
                controlGroupDetail.setContent2Score(0D);
                controlGroupDetail.setContent3Score(finalScore);
                controlGroupDetail.setContent4Score(0D);
                break;
            case Points.CONTENT4_SCORE:
                controlGroupDetail.setContent1Score(0D);
                controlGroupDetail.setContent2Score(0D);
                controlGroupDetail.setContent3Score(0D);
                controlGroupDetail.setContent4Score(finalScore);
                break;
        }

        iControlGroupAssessment.calculateControlGroupAssessmentScore(qualitativeAssess.getAnnual(), userId, bizObjectFacade, controlGroupDetail);

        log.info("计算定性考核完成");
        return getErrResponseResult(ErrCode.OK.getErrCode(), ErrCode.OK.getErrMsg());
    }

}
