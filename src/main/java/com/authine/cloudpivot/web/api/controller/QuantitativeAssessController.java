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
import com.authine.cloudpivot.web.api.utils.CreateAssessmentScoreSheetUtils;
import com.authine.cloudpivot.web.api.view.ResponseResult;
import io.swagger.util.Json;
import jodd.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    private static final String QUANTITATIVE_ASSESS = "quantitative_assess";
    private static final String COMPLETED = "COMPLETED";

    @PostMapping("/sendQuantitativeAssess")
    public ResponseResult<Void> sendQuantitativeAssess(@RequestBody SendQuantitativeAssess sendQuantitativeAssess) {
        log.info("开始创建定量考核流程");
        String userId = getUserId();
        log.info("当前用户id:" + userId);

        if (StringUtil.isEmpty(userId)) {
            userId = "2c9280a26706a73a016706a93ccf002b";
        }

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
            model.setSequenceStatus(COMPLETED);
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
            String id = bizObjectFacade.saveBizObject(userId, model, false);

            // 获取定量考核里面考核项
            Map<String, List<AssessmentScoreSheet>> aMap = CreateAssessmentScoreSheetUtils.create(id);
            List<AssessmentScoreSheet> aList = null;
            for (String key :
                    aMap.keySet()) {
                aList = aMap.get(key);
                switch (key) {
                    case "安全管理":
                        quantitativeAssessMapper.insertQuantitativeSafeManage(aList);
                        break;
                    case "质量管理":
                        quantitativeAssessMapper.insertQuantitativeQualityManage(aList);
                        break;
                    case "进度及施工组织管理":
                        quantitativeAssessMapper.insertQuantitativeScManage(aList);
                        break;
                    case "技术管理":
                        quantitativeAssessMapper.insertQuantitativeSkillManage(aList);
                        break;
                    case "工程分包管理":
                        quantitativeAssessMapper.insertQuantitativeEngineManag(aList);
                        break;
                    case "铁路、公路信用评价":
                        quantitativeAssessMapper.insertQuantitativeCreditEvaluat(aList);
                        break;
                    case "沟通协调、重大问题报告":
                        quantitativeAssessMapper.insertQuantitativeProblem(aList);
                        break;
                    case "管控工作规范性":
                        quantitativeAssessMapper.insertQuantitativeControl(aList);
                        break;
                    case "对片区内在建项目的服务、指导":
                        quantitativeAssessMapper.insertQuantitativeService(aList);
                        break;
                    case "稽查纪律":
                        quantitativeAssessMapper.insertQuantitativeDiscipline(aList);
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

    /**
     * @Author: wangyong
     * @Date: 2020/1/8 14:48
     * @param qaJudges : 评委列表
     * @param id : 定量考核id
     * @return : void
     * @Description: 给评委子表设置id以及指定parentid
     */
    private void setQaJudgesId(List<QaJudges> qaJudges, String id) {

        for (QaJudges q :
                qaJudges) {
            q.setId(UUID.randomUUID().toString().replaceAll("-", ""));
            q.setParentId(id);
        }
    }

}
