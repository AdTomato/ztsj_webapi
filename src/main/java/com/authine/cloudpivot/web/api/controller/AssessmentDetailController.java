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
import com.authine.cloudpivot.web.api.service.IAssessmentDetail;
import com.authine.cloudpivot.web.api.utils.CreateEvaluationTableUtils;
import com.authine.cloudpivot.web.api.view.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jodd.util.BCrypt;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@Api(description = "机关部门考核", tags = "机关部门考核接口")
@RestController
@RequestMapping("/ext/assessmentDetail")
@Slf4j
public class AssessmentDetailController extends BaseController {

    @Autowired
    IAssessmentDetail assessmentDetail;

    /**
     * 根据机关部门年度考核发起机关部门年度考核流程
     *
     * @param launchAssessment
     * @return
     */
    @ApiOperation(value = "发起机关部门年度考核流程", notes = "发起机关部门年度考核")
    @RequestMapping("/createAssessmentWorkflow")
    public ResponseResult<String> createAssessmentWorkflow(@RequestBody LaunchAssessment launchAssessment) {
        log.info("开始执行创建流程方法");
        // 部门列表
        List<Department> assessedDepartments = launchAssessment.getAssessedDepartment();
        // 评委列表
        List<User> judges = launchAssessment.getJudge();

        // 创建数据的引擎类
        BizObjectFacade bizObjectFacade = super.getBizObjectFacade();

        // 有关组织机构的引擎类
        OrganizationFacade organizationFacade = super.getOrganizationFacade();

        // 创建流程的引擎类
        WorkflowInstanceFacade workflowInstanceFacade = super.getWorkflowInstanceFacade();

        // 当前用户id
        String userId = super.getUserId();
        if (userId == null) {
            userId = "2c9280a26706a73a016706a93ccf002b";
        }
//        userId = "ff8080816e3e92fb016e3e9607a4004c";

        log.info("当前用户id：" + userId);
        log.info("传入参数数据" + launchAssessment.toString());

        // 用户类
        UserModel user = organizationFacade.getUser(userId);
        for (Department assessmentDepartment :
                assessedDepartments) {
            // 年度总结id
            String departmentAnnualId = assessmentDetail.getDepartmentAnnual(JSON.toJSONString(Arrays.asList(assessmentDepartment)), launchAssessment.getAnnual());
            log.info("年度总结id：" + departmentAnnualId);
            BizObjectModel model = new BizObjectModel();

            // 设置表单编码
            model.setSchemaCode("DepartmentAssessment");

            Map<String, Object> data = new HashMap<>();

            // 被考核部门
            data.put("assessed_department", JSON.toJSONString(Arrays.asList(assessmentDepartment)));

            // 考核主体
            data.put("assessed_content", launchAssessment.getAssessedContent());

            // 年度总结
            data.put("annual_summary", departmentAnnualId);

            // 年度
            data.put("annual", launchAssessment.getAnnual());

            // 评委
            data.put("judge", JSON.toJSONString(judges));

            // 将数据写入到model中
            model.put(data);

            log.info("存入数据库中的数据：" + data);

            // 创建机关部门打分表,返回机关部门打分表的id值
            String objectId = bizObjectFacade.saveBizObject(userId, model, false);

            log.info("机关部门打分表id：" + objectId);

            // 批量创建机关部门考核评价表
            assessmentDetail.insertEvaluationTable(CreateEvaluationTableUtils.getEvaluationTable(objectId));

//            List<EvaluationTable> evaluationTables = CreateEvaluationTableUtils.getEvaluationTable(objectId);
//            assessmentDetail.insertEvaluationTable(evaluationTables);
            // 开启机关部门打分表流程
            workflowInstanceFacade.startWorkflowInstance(user.getDepartmentId(), user.getId(), "departments_assessment_wf", objectId, true);
//            workflowInstanceFacade.submitWorkItem()
        }
        return getOkResponseResult("success", ErrCode.OK.getErrMsg());
    }

    /**
     * 批量创建机关部门考核明细
     *
     * @param adList
     * @return
     */
    @ApiOperation(value = "批量添加机关部门考核明细接口", notes = "批量添加机关部门考核明细接口")
    @RequestMapping("/insertAssessmentDetail")
    public ResponseResult<String> insertAssessmentDetail(@RequestBody List<AssessmentDetail> adList) {
        log.info("开始执行批量添加机关部门考核明细方法");
//        return adList;
        if (adList == null || adList.size() == 0) {
            return getErrResponseResult("error", ErrCode.SYS_PARAMETER_EMPTY.getErrCode(), ErrCode.SYS_PARAMETER_EMPTY.getErrMsg());
        }

        String userId = getUserId();
        Map<String, Object> checkMap = new HashMap();
        checkMap.put("id", adList.get(0).getDeartment_assessment());
        checkMap.put("userId", userId);
        Integer count = assessmentDetail.isCreateAssessmentDetail(checkMap);
        if (0 != count) {
            log.info("重复提交,不进行明细的存储");
            return getErrResponseResult("error", ErrCode.SYS_PARAMETER_EMPTY.getErrCode(), ErrCode.SYS_PARAMETER_EMPTY.getErrMsg());
        }

        List<BizObjectModel> models = new ArrayList<>();
        for (AssessmentDetail assessmentDetail :
                adList) {
            BizObjectModel model = new BizObjectModel();
            model.setSchemaCode("assessment_detail");
            Map<String, Object> map = new HashMap<>();
            // 设置关联机关部门考核
            map.put("deartment_assessment", assessmentDetail.getDeartment_assessment());

            // 设置测评项目
            map.put("assessment_project", assessmentDetail.getAssessment_project());

            // 设置得分
            map.put("score", assessmentDetail.getScore());
            model.put(map);
            // 数据状态为生效状态
            model.setSequenceStatus("COMPLETED");
            models.add(model);
        }

        // 创建数据的引擎类
        BizObjectFacade bizObjectFacade = super.getBizObjectFacade();
//        userId = "ff8080816e3e92fb016e3e9607a4004c";
        log.info("当前操作用户id：" + userId);
        // 使用引擎方法批量创建数据
        bizObjectFacade.addBizObjects(userId, models, "id");
        // 清空机关部门考核的每个人的打分项
        assessmentDetail.cleanAssessmentScore(adList.get(0).getDeartment_assessment());
        return getOkResponseResult("success", ErrCode.OK.getErrMsg());
    }

    /**
     * 汇总机关部门考核明细得出结果，将结果回写到机关部门评价表中
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "计算机关部门考核结果", notes = "计算机关部门考核结果")
    @RequestMapping("/calculationAssessmentResult")
    public ResponseResult<String> calculationAssessmentResult(@RequestBody String id) {
        log.info("当前计算的id值为：" + id);

        if (id == null || id.isEmpty()) {
            return getOkResponseResult("error", "传入的id值为空");  // 参数为空
        }

        String objectId = id.replaceAll("\"", "");
        objectId = objectId.replaceAll("=", "");

        log.info("开始执行计算机关部门考核结果方法");

        String sequenceStatus = assessmentDetail.getDepartmentAnnualStatus(objectId);
        sequenceStatus.replaceAll(" ", "");
        log.info("当前数据状态：" + sequenceStatus);
        if (!sequenceStatus.equals("COMPLETED")) {  // COMPLETED
            log.info("流程尚未结束，无需计算");
            return getOkResponseResult("error", "流程尚未结束，无需计算");  // 参数为空
        }

        DepartmentAssessment departmentAssessment = assessmentDetail.getDepartmentAssessmentById(objectId);

        // 通过parentId获取存放机关部门评价表结果的列表
        List<ADComprehensiveAssessment> adList = assessmentDetail.getADComprehensiveAssessmentByParentId(objectId);
        log.info("当前的列表为：" + adList);
        if (adList.size() == 0) {
            return getOkResponseResult("error", "没有计算数据");
        }
        Double totalScore = 0D;
        for (ADComprehensiveAssessment ad :
                adList) {
            // 获取结果的平均值
            Double resultScore = assessmentDetail.getAssessmentDetailResultScore(ad.getParentId(), ad.getAssessmentProject());
            totalScore += resultScore;
            log.info("结果：" + ad.getAssessmentProject() + ":" + resultScore + "  id:" + ad.getId());
            // 更新机关部门评价表结果
            assessmentDetail.updateAssessmentDetailResultScore(ad.getId(), resultScore);
        }

        log.info("总分：" + totalScore);
        log.info("departmentAssessment:" + departmentAssessment);
        String assessmentId = null;
        if (departmentAssessment != null) {
            assessmentId = assessmentDetail.getAssessmentIdByAnnual(departmentAssessment.getAnnual());
            if (assessmentId == null) {
                // 不存在年度考核得分汇总表，创建
                log.info("创建年度考核得分汇总表");
                assessmentId = insertAssessmentDetail(departmentAssessment.getAnnual(), getUserId());
            }

            if (assessmentId != null) {
                AssessmentSummaryDetail assessmentSummaryDetail = assessmentDetail.getAssessmentDetailByParentIdAndDepartment(assessmentId, departmentAssessment.getAssessedDepartment());
                if (assessmentSummaryDetail == null) {
                    // 不存在，创建
                    log.info("创建年度考核得分汇总表明细");
                    insertAssessmentSummaryDetail(departmentAssessment, totalScore, assessmentId);
                } else {
                    // 存在更新
                    log.info("更新年度考核得分汇总表明细");
                    updateAssessmentSummaryDetail(assessmentSummaryDetail, departmentAssessment, totalScore);
                }
            }
        }
        return getOkResponseResult("success", "成功计算结果");  // 成功
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
     * @param departmentAssessment 部门打分表
     * @param totalScore           总分
     */
    private void insertAssessmentSummaryDetail(DepartmentAssessment departmentAssessment, Double totalScore, String assessmentId) {
        AssessmentSummaryDetail assessmentSummaryDetail = new AssessmentSummaryDetail();
        assessmentSummaryDetail.setId(UUID.randomUUID().toString().replaceAll("-", ""));
        assessmentSummaryDetail.setParentId(assessmentId);
        assessmentSummaryDetail.setDepartment(departmentAssessment.getAssessedDepartment());
        switch (departmentAssessment.getAssessedContent()) {
            case "局领导":
            case "局机关部门主要负责人":
                assessmentSummaryDetail.setAnnualEvaluation(totalScore * 0.25);
                break;
            case "局属各单位、派出机构主要负责人":
                assessmentSummaryDetail.setAnnualEvaluation(totalScore * 0.5);
                break;
        }
        assessmentSummaryDetail.setFirstQuarter(0D);
        assessmentSummaryDetail.setSecondQuarter(0D);
        assessmentSummaryDetail.setThirdQuarter(0D);
        assessmentSummaryDetail.setFourQuarter(0D);
        assessmentSummaryDetail.setAnnualScore(assessmentSummaryDetail.getAnnualEvaluation() * 0.5);
        log.info("创建的明细：" + assessmentSummaryDetail);
        assessmentDetail.insertAssessmentSummaryDetail(assessmentSummaryDetail);
    }

    /**
     * 更新年度机关考核得分汇总表明细
     *
     * @param assessmentSummaryDetail 机关部门得分汇总表
     * @param departmentAssessment    年度考核打分表
     * @param totalScore              总分
     */
    private void updateAssessmentSummaryDetail(AssessmentSummaryDetail assessmentSummaryDetail, DepartmentAssessment departmentAssessment, Double totalScore) {

        Double score = 0D;
        switch (departmentAssessment.getAssessedContent()) {
            case "局领导":
            case "局机关部门主要负责人":
                score = totalScore * 0.25;
                break;
            case "局属各单位、派出机构主要负责人":
                score = totalScore * 0.5;
                break;
        }
        assessmentSummaryDetail.setAnnualEvaluation(assessmentSummaryDetail.getAnnualEvaluation() + score);
        Double annualEvaluation = 0D;
        annualEvaluation = (assessmentSummaryDetail.getFirstQuarter() +
                assessmentSummaryDetail.getSecondQuarter() +
                assessmentSummaryDetail.getThirdQuarter() +
                assessmentSummaryDetail.getFourQuarter()) / 4 * 0.5 + assessmentSummaryDetail.getAnnualEvaluation() * 0.5;
        assessmentSummaryDetail.setAnnualScore(annualEvaluation);
        assessmentDetail.updateAssessmentDetailById(assessmentSummaryDetail);
    }

    @GetMapping("/getPassword")
    private String getPassword(String pwd) {
        return BCrypt.hashpw(pwd, BCrypt.gensalt());
    }

}
