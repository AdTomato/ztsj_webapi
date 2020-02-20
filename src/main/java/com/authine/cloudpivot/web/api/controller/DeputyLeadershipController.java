package com.authine.cloudpivot.web.api.controller;

import com.authine.cloudpivot.engine.enums.ErrCode;
import com.authine.cloudpivot.web.api.bean.DeputyLeadershipDateSet;
import com.authine.cloudpivot.web.api.bean.DlaAssessmentDetail;
import com.authine.cloudpivot.web.api.bean.DldsAssessmentDetail;
import com.authine.cloudpivot.web.api.controller.base.BaseController;
import com.authine.cloudpivot.web.api.parameter.DeputyLeadershipUpdateAssessmentDetail;
import com.authine.cloudpivot.web.api.service.DeputyLeadershipService;
import com.authine.cloudpivot.web.api.utils.DoubleUtils;
import com.authine.cloudpivot.web.api.utils.Points;
import com.authine.cloudpivot.web.api.view.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: wangyong
 * @Date: 2020-02-18 16:00
 * @Description:
 */
@RestController
@RequestMapping("/ext/deputyLeadership")
@Slf4j
public class DeputyLeadershipController extends BaseController {

    @Autowired
    DeputyLeadershipService deputyLeadershipService;

    @PostMapping("/updateAssessmentDetail")
    public ResponseResult<Object> updateAssessmentDetail(@RequestBody DeputyLeadershipUpdateAssessmentDetail params) {
        synchronized (DeputyLeadershipController.class) {
            if (params == null || StringUtils.isEmpty(params.getParentId()) || params.getDlaAssessmentDetails() == null || params.getDlaAssessmentDetails().size() == 0) {
                return this.getErrResponseResult(null, 404L, "参数为空");
            }
            String parentId = params.getParentId();
            List<DlaAssessmentDetail> dlaAssessmentDetails = params.getDlaAssessmentDetails();
            log.info("开始更新副职及以上领导人员考核数据");
            // 获取数据设置里面的明细
            List<DldsAssessmentDetail> assessmentDetails = deputyLeadershipService.getAssessmentDetailByParentId(parentId);
            // 用一个map存储明细，方便查询
            Map<String, DldsAssessmentDetail> data = new HashMap<>();
            for (DldsAssessmentDetail assessmentDetail : assessmentDetails) {
                data.put(assessmentDetail.getId(), assessmentDetail);
            }

            for (DlaAssessmentDetail assessmentDetail : dlaAssessmentDetails) {
                DldsAssessmentDetail assessmentDetail1 = data.get(assessmentDetail.getPid());
                if (null != assessmentDetail1) {
                    dataSet(assessmentDetail1, assessmentDetail.getEvaluationOpinion());
                }
            }

            // 将结果更新回去
            deputyLeadershipService.updateAssessmentDetail(assessmentDetails);
            log.info("更新成功");
        }
        return this.getErrResponseResult(null, ErrCode.OK.getErrCode(), ErrCode.OK.getErrMsg());
    }

    @GetMapping("/getDeputyLeadershipDataSet")
    public ResponseResult<Object> getDeputyLeadershipDateSet() {
        DeputyLeadershipDateSet deputyLeadershipDateSet = deputyLeadershipService.getDeputyLeadershipDateSet();
        return this.getErrResponseResult(deputyLeadershipDateSet, ErrCode.OK.getErrCode(), ErrCode.OK.getErrMsg());
    }

    @GetMapping("/getDeputyLeadershipDataSetById")
    public ResponseResult<Object> getDeputyLeadershipDataSetById(@RequestParam String id) {
        DeputyLeadershipDateSet deputyLeadershipDateSet = deputyLeadershipService.getDeputyLeadershipDateSetById(id);
        if (deputyLeadershipDateSet == null) {
            return this.getErrResponseResult(null, 404L, "没有获取数据");
        }
        return this.getErrResponseResult(deputyLeadershipDateSet, ErrCode.OK.getErrCode(), ErrCode.OK.getErrMsg());
    }

    private void dataSet(DldsAssessmentDetail assessmentDetail1, String evaluationOpinion) {
        switch (evaluationOpinion) {
            case Points
                    .EXCELLENT_POINT:
                assessmentDetail1.setExcellentPoint(DoubleUtils.nullToDouble(assessmentDetail1.getExcellentPoint()) + 1);
                break;
            case Points.COMPETENT_POINT:
                assessmentDetail1.setCompetentPoint(DoubleUtils.nullToDouble(assessmentDetail1.getCompetentPoint()) + 1);
                break;
            case Points.BASIC_COMPETENCE_POINT:
                assessmentDetail1.setBasicCompetencePoint(DoubleUtils.nullToDouble(assessmentDetail1.getBasicCompetencePoint()) + 1);
                break;
            case Points.INCOMPETENT_POINT:
                assessmentDetail1.setIncompetentPoint(DoubleUtils.nullToDouble(assessmentDetail1.getIncompetentPoint()) + 1);
                break;
        }
    }

}
