package com.authine.cloudpivot.web.api.service.impl;

import com.authine.cloudpivot.engine.api.facade.BizObjectFacade;
import com.authine.cloudpivot.engine.api.model.runtime.BizObjectModel;
import com.authine.cloudpivot.web.api.bean.ControlGroupDetail;
import com.authine.cloudpivot.web.api.mapper.ControlGroupAssessmentMapper;
import com.authine.cloudpivot.web.api.service.IControlGroupAssessment;
import com.authine.cloudpivot.web.api.utils.DoubleUtils;
import com.authine.cloudpivot.web.api.utils.Points;
import jodd.util.MathUtil;
import jodd.util.StringUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @Author: wangyong
 * @Date: 2020-01-10 14:21
 * @Description: 管控组负责人年度考核service层
 */
@Service
public class ControlGroupAssessmentImpl implements IControlGroupAssessment {

    @Resource
    ControlGroupAssessmentMapper controlGroupAssessmentMapper;

    private static final String CONTROL_GROUP_ASSESSMENT = "control_group_assessment";

    @Override
    public String getControlGroupAssessmentIdByAnnual(String annual) {
        return controlGroupAssessmentMapper.getControlGroupAssessmentIdByAnnual(annual);
    }

    @Override
    public void insertControlGroupDetails(List<ControlGroupDetail> controlGroupDetailList) {
        controlGroupAssessmentMapper.insertControlGroupDetails(controlGroupDetailList);
    }

    @Override
    public void insertControlGroupDetail(ControlGroupDetail controlGroupDetail) {
        controlGroupAssessmentMapper.insertControlGroupDetail(controlGroupDetail);
    }

    @Override
    public void getControlGroupDetailById(String id) {
        controlGroupAssessmentMapper.getControlGroupDetailById(id);
    }

    @Override
    public void updateControlGroupDetailById(ControlGroupDetail controlGroupDetail) {
        controlGroupAssessmentMapper.updateControlGroupDetailById(controlGroupDetail);
    }

    @Override
    public String createControlGroupAssessment(BizObjectFacade bizObjectFacade, String annual, String userId) {
        BizObjectModel model = new BizObjectModel();
        model.setSequenceStatus(Points.COMPLETED);
        model.setSchemaCode(CONTROL_GROUP_ASSESSMENT);
        Map map = new HashMap();
        map.put("annual", annual);
        model.put(map);
        return bizObjectFacade.saveBizObject(userId, model, true);
    }

    @Override
    public ControlGroupDetail getControlGroupDetailByIdAndAssessee(Map map) {
        return controlGroupAssessmentMapper.getControlGroupDetailByIdAndAssessee(map);
    }

    @Override
    public synchronized void calculateControlGroupAssessmentScore(String annual, String userId, BizObjectFacade bizObjectFacade, ControlGroupDetail controlGroupDetail) {
        String id = getControlGroupAssessmentIdByAnnual(annual);
        if (StringUtil.isEmpty(id)) {
            id = createControlGroupAssessment(bizObjectFacade, annual, userId);
        }

        Map map = new HashMap();
        map.put("id", id);
        map.put("assessee", controlGroupDetail.getAssessee());
        ControlGroupDetail c = getControlGroupDetailByIdAndAssessee(map);
        if (null == c) {
            // 不存在, 需要创建
            controlGroupDetail.setId(UUID.randomUUID().toString().replaceAll("-", ""));
            controlGroupDetail.setParentId(id);
            controlGroupDetail.setQualitativeScore(DoubleUtils.doubleRound(controlGroupDetail.getContent1Score() * 0.4 + controlGroupDetail.getContent2Score() * 0.2 + controlGroupDetail.getContent3Score() * 0.2 + controlGroupDetail.getContent4Score() * 0.2, 2));
            controlGroupDetail.setFinalScore(DoubleUtils.doubleRound(controlGroupDetail.getQuantitativeScore() * 0.7 + controlGroupDetail.getQualitativeScore() * 0.3, 2));
            insertControlGroupDetail(controlGroupDetail);
        } else {
            // 存在, 需要更新
            if (controlGroupDetail.getQuantitativeScore() != 0D) {
                c.setQuantitativeScore(controlGroupDetail.getQuantitativeScore());
            } else if (controlGroupDetail.getContent1Score() != 0D) {
                c.setContent1Score(controlGroupDetail.getContent1Score());
            } else if (controlGroupDetail.getContent2Score() != 0D) {
                c.setContent2Score(controlGroupDetail.getContent2Score());
            } else if (controlGroupDetail.getContent3Score() != 0D) {
                c.setContent3Score(controlGroupDetail.getContent3Score());
            } else if (controlGroupDetail.getContent4Score() != 0D) {
                c.setContent4Score(controlGroupDetail.getContent4Score());
            }
            c.setQualitativeScore(DoubleUtils.doubleRound(c.getContent1Score() * 0.4 + c.getContent2Score() * 0.2 + c.getContent3Score() * 0.2 + c.getContent4Score() * 0.2, 2));
            c.setFinalScore(DoubleUtils.doubleRound(c.getQuantitativeScore() * 0.7 + c.getQualitativeScore() * 0.3, 2));
            updateControlGroupDetailById(c);
        }

    }


}
