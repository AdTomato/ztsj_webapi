package com.authine.cloudpivot.web.api.service.impl;

import com.authine.cloudpivot.web.api.bean.PaContent;
import com.authine.cloudpivot.web.api.bean.PerformanceAssessment;
import com.authine.cloudpivot.web.api.bean.PerformanceAssessmentDet;
import com.authine.cloudpivot.web.api.bean.SpaAssessmentPeople;
import com.authine.cloudpivot.web.api.dto.PerformanceAssessmentDto;
import com.authine.cloudpivot.web.api.mapper.PerformanceAssessmentMapper;
import com.authine.cloudpivot.web.api.service.PerformanceAssessmentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: wangyong
 * @Date: 2020-02-26 00:55
 * @Description: 履职考核service
 */
@Service
public class PerformanceAssessmentServiceImpl implements PerformanceAssessmentService {

    @Resource
    PerformanceAssessmentMapper performanceAssessmentMapper;

    @Override
    public void insertPerformanceAssessments(List<PerformanceAssessment> performanceAssessments) {
        performanceAssessmentMapper.insertPerformanceAssessments(performanceAssessments);
    }

    @Override
    public void insertPaContents(List<PaContent> paContents) {
        performanceAssessmentMapper.insertPaContents(paContents);
    }

    @Override
    public void insertPerformanceAssessmentDets(List<PerformanceAssessmentDet> performanceAssessmentDets) {
        performanceAssessmentMapper.insertPerformanceAssessmentDets(performanceAssessmentDets);
    }

    @Override
    public PerformanceAssessmentDto getPerformanceAssessmentDto(String id) {
        return performanceAssessmentMapper.getPerformanceAssessmentDto(id);
    }

    @Override
    public List<PerformanceAssessmentDet> getPerformanceAssessmentDets(String id) {
        return performanceAssessmentMapper.getPerformanceAssessmentDets(id);
    }

    @Override
    public void updatePerformanceAssessment(PerformanceAssessmentDto performanceAssessmentDto) {
        performanceAssessmentMapper.updatePerformanceAssessment(performanceAssessmentDto);
    }

    @Override
    public void updatePaContent(List<PaContent> paContents) {
        performanceAssessmentMapper.updatePaContent(paContents);
    }

    @Override
    public List<SpaAssessmentPeople> getSpaAssessmentPeoples(String id) {
        return performanceAssessmentMapper.getSpaAssessmentPeoples(id);
    }

    @Override
    public void updateSpaAssessmentPeople(PerformanceAssessmentDto performanceAssessmentDto) {
        performanceAssessmentMapper.updateSpaAssessmentPeople(performanceAssessmentDto);
    }

    @Override
    public void clearPaContent(String id) {
        performanceAssessmentMapper.clearPaContent(id);
    }

    @Override
    @Transactional
    public List<String> getPerformanceAssessmentDetNum(String id) {
        return performanceAssessmentMapper.getPerformanceAssessmentDetNum(id);
    }
}
