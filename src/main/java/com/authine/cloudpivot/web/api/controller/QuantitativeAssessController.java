package com.authine.cloudpivot.web.api.controller;

import com.alibaba.fastjson.JSON;
import com.authine.cloudpivot.engine.api.facade.BizObjectFacade;
import com.authine.cloudpivot.engine.api.facade.OrganizationFacade;
import com.authine.cloudpivot.engine.api.facade.WorkflowInstanceFacade;
import com.authine.cloudpivot.engine.api.model.organization.DepartmentModel;
import com.authine.cloudpivot.engine.api.model.organization.UserModel;
import com.authine.cloudpivot.engine.api.model.runtime.BizObjectModel;
import com.authine.cloudpivot.engine.enums.ErrCode;
import com.authine.cloudpivot.web.api.bean.*;
import com.authine.cloudpivot.web.api.controller.base.BaseController;
import com.authine.cloudpivot.web.api.mapper.QuantitativeAssessMapper;
import com.authine.cloudpivot.web.api.service.IControlGroupAssessment;
import com.authine.cloudpivot.web.api.service.IQuantitativeAssessService;
import com.authine.cloudpivot.web.api.utils.CreateAssessmentScoreSheetUtils;
import com.authine.cloudpivot.web.api.utils.DoubleUtils;
import com.authine.cloudpivot.web.api.utils.Points;
import com.authine.cloudpivot.web.api.utils.UserUtils;
import com.authine.cloudpivot.web.api.view.ResponseResult;
import jodd.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.nustaq.offheap.structs.structtypes.StructInt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;

import javax.annotation.Resource;
import java.util.*;

/**
 * @Author: wangyong
 * @Date: 2020-01-08 11:20
 * @Description: 管控组负责人年度定量考核Controller层
 */
@RestController
@RequestMapping("/ext/quantitativeAssess")
@Slf4j
public class QuantitativeAssessController extends BaseController {

    @Resource
    QuantitativeAssessMapper quantitativeAssessMapper;

    @Autowired
    IQuantitativeAssessService quantitativeAssessService;

    @Autowired
    IControlGroupAssessment controlGroupAssessment;

    private static final String QUANTITATIVE_ASSESS = "quantitative_assess";
    private static final String QUANTITATIVE_ASSESS_DETAIL = "quantitative_assess_detail";
    private static final String COMPLETED = "COMPLETED";
    private static final String ADMIN_ID = "2c9280a26706a73a016706a93ccf002b";

    @PostMapping("/sendQuantitativeAssess")
    public ResponseResult<Void> sendQuantitativeAssess(@RequestBody SendQuantitativeAssess sendQuantitativeAssess) {
        log.info("开始创建定量考核流程");
        String userId = UserUtils.getUserId(getUserId());
        log.info("当前用户id:" + userId);

        UserModel user = getOrganizationFacade().getUser(userId);

        BizObjectFacade bizObjectFacade = getBizObjectFacade();
        WorkflowInstanceFacade workflowInstanceFacade = getWorkflowInstanceFacade();
        OrganizationFacade organizationFacade = getOrganizationFacade();

        if (null == sendQuantitativeAssess) {
            // 参数为空
            return getErrResponseResult(ErrCode.SYS_PARAMETER_EMPTY.getErrCode(), ErrCode.SYS_PARAMETER_EMPTY.getErrMsg());
        }

        // 创建定量考核评分表子表内容
        List<QaJudges> qaJudges = new ArrayList<>();
        for (SqaJudges sqas :
                sendQuantitativeAssess.getSqaJudges()) {
            QaJudges q = new QaJudges();
            q.setDepartment(JSON.toJSONString(sqas.getDepartment()));
            q.setJudge(JSON.toJSONString(sqas.getJudge()));
            qaJudges.add(q);
        }

        // 创建定量考核评分表主表内容
        List<User> assesseeList = sendQuantitativeAssess.getAssessee();
        List<DepartmentModel> departmentModels = null;
        Department department = null;
        for (User assessee :
                assesseeList) {
            BizObjectModel model = new BizObjectModel();
            model.setSchemaCode(QUANTITATIVE_ASSESS);
//            model.setSequenceStatus(COMPLETED);
            Map map = new HashMap();

            // 设置被考核人
            map.put("assessee", JSON.toJSONString(Arrays.asList(assessee)));

            // 设置被考核人的单位
            departmentModels = organizationFacade.getDepartmentsByUserId(assessee.getId());
            department = new Department();
            department.setId(departmentModels.get(0).getId());
            department.setType(departmentModels.get(0).getDeptType().getIndex());
//            departmentModels.get(0).ge
            map.put("unit", JSON.toJSONString(Arrays.asList(department)));

            // 设置年度
            map.put("annual", sendQuantitativeAssess.getAnnual());

            // 设置评委
            map.put("judge", JSON.toJSONString(sendQuantitativeAssess.getJudge()));

            model.put(map);

            // 创建定量考核主表
            String id = bizObjectFacade.saveBizObject(userId, model, true);

            // 获取定量考核里面考核项
            Map<String, List<AssessmentScoreSheet>> aMap = CreateAssessmentScoreSheetUtils.create(id);
            List<AssessmentScoreSheet> aList = null;
            for (String key :
                    aMap.keySet()) {
                aList = aMap.get(key);
                switch (key) {
                    case "安全管理":
                        quantitativeAssessService.insertQuantitativeSafeManage(aList);
                        break;
                    case "质量管理":
                        quantitativeAssessService.insertQuantitativeQualityManage(aList);
                        break;
                    case "进度及施工组织管理":
                        quantitativeAssessService.insertQuantitativeScManage(aList);
                        break;
                    case "技术管理":
                        quantitativeAssessService.insertQuantitativeSkillManage(aList);
                        break;
                    case "工程分包管理":
                        quantitativeAssessService.insertQuantitativeEngineManag(aList);
                        break;
                    case "铁路、公路信用评价":
                        quantitativeAssessService.insertQuantitativeCreditEvaluat(aList);
                        break;
                    case "沟通协调、重大问题报告":
                        quantitativeAssessService.insertQuantitativeProblem(aList);
                        break;
                    case "管控工作规范性":
                        quantitativeAssessService.insertQuantitativeControl(aList);
                        break;
                    case "对片区内在建项目的服务、指导":
                        quantitativeAssessService.insertQuantitativeService(aList);
                        break;
                    case "稽查纪律":
                        quantitativeAssessService.insertQuantitativeDiscipline(aList);
                        break;

                }
            }

            // 为评委子表设置id以及父id
            setQaJudgesId(qaJudges, id);

            // 创建定量考核评委子表
            quantitativeAssessMapper.insertQaJudges(qaJudges);

            // 发起定量考核流程
            workflowInstanceFacade.startWorkflowInstance(user.getDepartmentId(), userId, "quantitative_assess_wf", id, true);
        }
        log.info("创建定量考核流程成功");
        return getErrResponseResult(ErrCode.OK.getErrCode(), ErrCode.OK.getErrMsg());
    }

    @PostMapping("/insertQuantitativeAssessDetail")
    public ResponseResult<Void> insertQuantitativeAssessDetail(@RequestBody SaveQuantitativeAssessDetail saveQuantitativeAssessDetail) {
        log.info("开始创建定量考核明细");
        BizObjectFacade bizObjectFacade = getBizObjectFacade();
        String userId = UserUtils.getUserId(getUserId());

        if (null == saveQuantitativeAssessDetail) {
            // 参数为空
            return getErrResponseResult(ErrCode.SYS_PARAMETER_EMPTY.getErrCode(), ErrCode.SYS_PARAMETER_EMPTY.getErrMsg());
        }

        List<BizObjectModel> models = new ArrayList<>();
        String id = saveQuantitativeAssessDetail.getId();

        setAssessDetail(models, saveQuantitativeAssessDetail.getQuantitativeSafeManage(), id, Points.SAFE);
        setAssessDetail(models, saveQuantitativeAssessDetail.getQuantitativeQualityManage(), id, Points.QUALITY);
        setAssessDetail(models, saveQuantitativeAssessDetail.getQuantitativeScManage(), id, Points.SC);
        setAssessDetail(models, saveQuantitativeAssessDetail.getQuantitativeSkillManage(), id, Points.SKILL);
        setAssessDetail(models, saveQuantitativeAssessDetail.getQuantitativeEngineManag(), id, Points.ENGINE);
        setAssessDetail(models, saveQuantitativeAssessDetail.getQuantitativeCreditEvaluat(), id, Points.CREDIT);
        setAssessDetail(models, saveQuantitativeAssessDetail.getQuantitativeProblem(), id, Points.PROBLEM);
        setAssessDetail(models, saveQuantitativeAssessDetail.getQuantitativeControl(), id, Points.CONTROL);
        setAssessDetail(models, saveQuantitativeAssessDetail.getQuantitativeService(), id, Points.SERVICE);
        setAssessDetail(models, saveQuantitativeAssessDetail.getQuantitativeDiscipline(), id, Points.DISCIPLINE);

        // 批量创建明细
        bizObjectFacade.addBizObjects(userId, models, "id");
        return getErrResponseResult(ErrCode.OK.getErrCode(), ErrCode.OK.getErrMsg());
    }

    @PutMapping("/calculateQuantitativeAssess")
    public ResponseResult<Void> calculateQuantitativeAssess(@RequestParam String id, @RequestParam Integer num) {

        if (null == id) {
            return getErrResponseResult(ErrCode.SYS_PARAMETER_EMPTY.getErrCode(), ErrCode.SYS_PARAMETER_EMPTY.getErrMsg());
        }
        log.info("清空定量考核明细");
        quantitativeAssessMapper.cleanQuantitativeControlById(id);
        quantitativeAssessMapper.cleanQuantitativeCreditEvaluatById(id);
        quantitativeAssessMapper.cleanQuantitativeDisciplineById(id);
        quantitativeAssessMapper.cleanQuantitativeEngineManagById(id);
        quantitativeAssessMapper.cleanQuantitativeProblemById(id);
        quantitativeAssessMapper.cleanQuantitativeQualityManageById(id);
        quantitativeAssessMapper.cleanQuantitativeSafeManageById(id);
        quantitativeAssessMapper.cleanQuantitativeScManageById(id);
        quantitativeAssessMapper.cleanQuantitativeServiceById(id);
        quantitativeAssessMapper.cleanQuantitativeSkillManageById(id);

        String userId = UserUtils.getUserId(getUserId());

        synchronized (QuantitativeAssessController.class) {
            log.info("开始查询当前提交人数");
            List<String> peoples = quantitativeAssessMapper.getQuantitativeAssessDetailsNum(id);
            log.info("当前提交人数：" + peoples.size());
            if (peoples.size() != num) {
                log.info("流程尚未结束，无需计算");
                return getErrResponseResult(ErrCode.PERMISSION_MANAGER_TYPE_ERR.getErrCode(), "流程尚未结束，无需计算");  // 参数为空
            }
        }

        // 获取定量考核主表内容
        QuantitativeAssess quantitativeAssess = quantitativeAssessMapper.getQuantitativeAssessById(id);

        Map<String, AssessmentScoreSheet> map = new HashMap<>();
        // 获取安全管理内容
        List<AssessmentScoreSheet> safeList = quantitativeAssessMapper.getQuantitativeSafeManageByParentId(id);
        setQuantitativeAssessDetail(map, safeList);

        // 获取质量管理内容
        List<AssessmentScoreSheet> qualityList = quantitativeAssessMapper.getQuantitativeQualityManageByParentId(id);
        setQuantitativeAssessDetail(map, qualityList);

        // 获取进度及施工组织管理内容
        List<AssessmentScoreSheet> scList = quantitativeAssessMapper.getQuantitativeScManageByParentId(id);
        setQuantitativeAssessDetail(map, scList);

        // 获取技术管理内容
        List<AssessmentScoreSheet> skillList = quantitativeAssessMapper.getQuantitativeSkillManageByParentId(id);
        setQuantitativeAssessDetail(map, skillList);

        // 获取工程分包管理内容
        List<AssessmentScoreSheet> engineList = quantitativeAssessMapper.getQuantitativeEngineManagByParentId(id);
        setQuantitativeAssessDetail(map, engineList);

        // 获取铁路、公路信用评价内容
        List<AssessmentScoreSheet> creditList = quantitativeAssessMapper.getQuantitativeCreditEvaluatByParentId(id);
        setQuantitativeAssessDetail(map, creditList);

        // 沟通协调、重大问题报告
        List<AssessmentScoreSheet> problemList = quantitativeAssessMapper.getQuantitativeProblemByParentId(id);
        setQuantitativeAssessDetail(map, problemList);

        // 管控工作规范性
        List<AssessmentScoreSheet> controlList = quantitativeAssessMapper.getQuantitativeControlByParentId(id);
        setQuantitativeAssessDetail(map, controlList);

        // 对片区内在建项目的服务、指导
        List<AssessmentScoreSheet> serviceList = quantitativeAssessMapper.getQuantitativeServiceByParentId(id);
        setQuantitativeAssessDetail(map, serviceList);

        // 稽查纪律
        List<AssessmentScoreSheet> disciplineList = quantitativeAssessMapper.getQuantitativeDisciplineByParentId(id);
        setQuantitativeAssessDetail(map, disciplineList);

        List<QuantitativeAssessDetail> saveQuantitativeAssessDetailList = quantitativeAssessMapper.getQuantitativeAssessDetailById(id);

        Map<String, Double> score = new HashMap<>();
        for (QuantitativeAssessDetail detail :
                saveQuantitativeAssessDetailList) {
            AssessmentScoreSheet scoreSheet = map.get(detail.getSheetId());
            if (null != scoreSheet) {
                if (!StringUtils.isEmpty(detail.getDeductionReason())) {
                    scoreSheet.setDeductionReason(scoreSheet.getDeductionReason() + detail.getDeductionReason() + "\n");
                }
                if (null != detail.getDeduction()) {
                    scoreSheet.setDeduction(scoreSheet.getDeduction() + detail.getDeduction());
                }
                if (score.containsKey(detail.getAssessmentContent())) {
                    score.put(detail.getAssessmentContent(), checkDoubleIsNull(score.get(detail.getAssessmentContent())) + checkDoubleIsNull(detail.getDeduction()));
                } else {
                    score.put(detail.getAssessmentContent(), detail.getDeduction());
                }
            }
        }
        Double finalScore = 0D;
        for (String key :
                score.keySet()) {
            Double s = score.get(key);
            finalScore += s;
            switch (key) {
                case Points.SAFE:
                    quantitativeAssess.setSafeDeductionScore(s);
                    break;
                case Points.QUALITY:
                    quantitativeAssess.setQualityDeductionScore(s);
                    break;
                case Points.SC:
                    quantitativeAssess.setScDeductionScore(s);
                    break;
                case Points.SKILL:
                    quantitativeAssess.setSkillDeductionScore(s);
                    break;
                case Points.ENGINE:
                    quantitativeAssess.setEnginDeductionScore(s);
                    break;
                case Points.CREDIT:
                    quantitativeAssess.setCreditDeductionScore(s);
                    break;
                case Points.PROBLEM:
                    quantitativeAssess.setProblemDeductionScore(s);
                    break;
                case Points.CONTROL:
                    quantitativeAssess.setControlDeductionScore(s);
                    break;
                case Points.SERVICE:
                    quantitativeAssess.setServiceDeductionScore(s);
                    break;
                case Points.DISCIPLINE:
                    quantitativeAssess.setDisciplineDeductionScore(s);
                    break;
            }
        }

        quantitativeAssessMapper.updateQuantitativeAssessById(quantitativeAssess);
        log.info("更新定量考核主表完成");
        quantitativeAssessMapper.updateQuantitativeSafeManageById(safeList);
        log.info("更新安全管理完成");
        quantitativeAssessMapper.updateQuantitativeQualityManageById(qualityList);
        log.info("更新质量管理完成");
        quantitativeAssessMapper.updateQuantitativeScManageById(scList);
        log.info("更新进度及施工组织管理完成");
        quantitativeAssessMapper.updateQuantitativeSkillManageById(skillList);
        log.info("更新技术管理完成");
        quantitativeAssessMapper.updateQuantitativeEngineManagById(engineList);
        log.info("更新工程分包管理完成");
        quantitativeAssessMapper.updateQuantitativeCreditEvaluatById(creditList);
        log.info("更新铁路、公路信用评价完成");
        quantitativeAssessMapper.updateQuantitativeProblemById(problemList);
        log.info("更新沟通协调、重大问题报告完成");
        quantitativeAssessMapper.updateQuantitativeControlById(controlList);
        log.info("更新管控工作规范性完成");
        quantitativeAssessMapper.updateQuantitativeServiceById(serviceList);
        log.info("更新对片区内在建项目的服务、指导完成");
        quantitativeAssessMapper.updateQuantitativeDisciplineById(disciplineList);
        log.info("更新稽查纪律完成");
        log.info("管控组定量考核结果计算完毕");
        ControlGroupDetail controlGroupDetail = new ControlGroupDetail();
        controlGroupDetail.setQuantitativeScore(100 - finalScore);
        controlGroupDetail.setContent1Score(0D);
        controlGroupDetail.setContent2Score(0D);
        controlGroupDetail.setContent3Score(0D);
        controlGroupDetail.setContent4Score(0D);
        controlGroupDetail.setAssessee(quantitativeAssess.getAssessee());
        controlGroupAssessment.calculateControlGroupAssessmentScore(quantitativeAssess.getAnnual(), userId, getBizObjectFacade(), controlGroupDetail);
        log.info("更新汇总表成功");
        return getErrResponseResult(ErrCode.OK.getErrCode(), ErrCode.OK.getErrMsg());
    }

    /**
     * @param qaJudges : 评委列表
     * @param id       : 定量考核id
     * @return : void
     * @Author: wangyong
     * @Date: 2020/1/8 14:48
     * @Description: 给评委子表设置id以及指定parentid
     */
    private void setQaJudgesId(List<QaJudges> qaJudges, String id) {

        for (QaJudges q :
                qaJudges) {
            q.setId(UUID.randomUUID().toString().replaceAll("-", ""));
            q.setParentId(id);
        }
    }

    /**
     * @param models                   : 储存明细列表
     * @param assessmentScoreSheetList : 打分明细
     * @param id                       : 定量考核id
     * @param content                  : 考核主体
     * @return : void
     * @Author: wangyong
     * @Date: 2020/1/9 0:35
     * @Description: 用于生成定量考核明细
     */
    private void setAssessDetail(List<BizObjectModel> models, List<AssessmentScoreSheet> assessmentScoreSheetList, String id, String content) {
        if (null != assessmentScoreSheetList && 0 != assessmentScoreSheetList.size()) {
            for (AssessmentScoreSheet assessmentScoreSheet :
                    assessmentScoreSheetList) {
                if (0 != checkDoubleIsNull(assessmentScoreSheet.getDeduction())) {
                    BizObjectModel model = new BizObjectModel();
                    model.setSchemaCode(QUANTITATIVE_ASSESS_DETAIL);
                    model.setSequenceStatus(COMPLETED);
                    Map map = new HashMap();
                    map.put("quantitative_assess", id);
                    map.put("assessment_content", content);
                    map.put("sheet_id", assessmentScoreSheet.getId());
                    map.put("deduction_reason", assessmentScoreSheet.getDeductionReason());
                    map.put("deduction", assessmentScoreSheet.getDeduction());
                    model.put(map);
                    models.add(model);
                }
            }
        }
    }

    /**
     * @param map  : 用于存储明细定量考核明细的集合
     * @param list : 定量考核明细
     * @return : void
     * @Author: wangyong
     * @Date: 2020/1/9 15:06
     * @Description: 用于设置定量考核集合
     */
    private void setQuantitativeAssessDetail(Map map, List<AssessmentScoreSheet> list) {

        if (null != list && 0 != list.size()) {
            for (AssessmentScoreSheet assessmentScoreSheet :
                    list) {
                assessmentScoreSheet.setDeduction(0D);
                assessmentScoreSheet.setDeductionReason("");
                map.put(assessmentScoreSheet.getId(), assessmentScoreSheet);
            }
        }
    }

    /**
     * @param d : 所需判断的double类型
     * @return : java.lang.Double
     * @Author: wangyong
     * @Date: 2020/1/13 14:57
     * @Description: 判断double是否是null
     */
    private Double checkDoubleIsNull(Double d) {

        if (null == d) {
            return 0D;
        } else {
            return d;
        }
    }


}
