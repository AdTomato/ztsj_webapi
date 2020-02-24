package com.authine.cloudpivot.web.api.service.impl;

import com.authine.cloudpivot.web.api.bean.DeputyLeadershipDateSet;
import com.authine.cloudpivot.web.api.bean.DlaAssessmentDetail;
import com.authine.cloudpivot.web.api.bean.DldsAssessmentDetail;
import com.authine.cloudpivot.web.api.mapper.DeputyLeadershipMapper;
import com.authine.cloudpivot.web.api.service.DeputyLeadershipService;
import com.authine.cloudpivot.web.api.utils.DoubleUtils;
import com.authine.cloudpivot.web.api.utils.Points;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: wangyong
 * @Date: 2020-02-18 15:48
 * @Description:
 */
@Service
public class DeputyLeadershipServiceImpl implements DeputyLeadershipService {

    @Resource
    DeputyLeadershipMapper deputyLeadershipMapper;


    @Override
    public List<DldsAssessmentDetail> getAssessmentDetailByParentId(String parentId) {
        return deputyLeadershipMapper.getAssessmentDetailByParentId(parentId);
    }

    @Override
    public void updateAssessmentDetail(List<DldsAssessmentDetail> dldsAssessmentDetails) {
        deputyLeadershipMapper.updateAssessmentDetail(dldsAssessmentDetails);
    }

    @Override
    public DeputyLeadershipDateSet getDeputyLeadershipDateSet() {
        return deputyLeadershipMapper.getDeputyLeadershipDateSet();
    }

    @Override
    @Cacheable(cacheNames = {"deputy"}, key = "#id")
    public DeputyLeadershipDateSet getDeputyLeadershipDateSetById(String id) {
        return deputyLeadershipMapper.getDeputyLeadershipDateSetById(id);
    }

    @Override
    public Integer isCanSubmit(String ip) {
        return deputyLeadershipMapper.isCanSubmit(ip);
    }

    @Override
    @Transactional
    public void updateData(String parentId, List<DlaAssessmentDetail> dlaAssessmentDetails) {
        // 获取数据设置里面的明细
        List<DldsAssessmentDetail> assessmentDetails = getAssessmentDetailByParentId(parentId);
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
        updateAssessmentDetail(assessmentDetails);
    }

    @Override
    public List<DlaAssessmentDetail> getAllDlaAssessmentDetail(String did) {
        return deputyLeadershipMapper.getAllDlaAssessmentDetail(did);
    }

    @Override
    public String getNewDeputyLeadershipDateSetId() {
        return deputyLeadershipMapper.getNewDeputyLeadershipDateSetId();
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

