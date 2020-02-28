package com.authine.cloudpivot.web.api.service;

import com.authine.cloudpivot.web.api.bean.PaContent;
import com.authine.cloudpivot.web.api.bean.PerformanceAssessment;
import com.authine.cloudpivot.web.api.bean.PerformanceAssessmentDet;
import com.authine.cloudpivot.web.api.bean.SpaAssessmentPeople;
import com.authine.cloudpivot.web.api.dto.PerformanceAssessmentDto;

import java.util.List;

/**
 * @Author: wangyong
 * @Date: 2020-02-26 00:55
 * @Description: 履职考核service接口
 */
public interface PerformanceAssessmentService {
    void insertPerformanceAssessments(List<PerformanceAssessment> performanceAssessments);

    void insertPaContents(List<PaContent> paContents);

    void insertPerformanceAssessmentDets(List<PerformanceAssessmentDet> performanceAssessmentDets);

    PerformanceAssessmentDto getPerformanceAssessmentDto(String id);

    List<PerformanceAssessmentDet> getPerformanceAssessmentDets(String id);

    void updatePerformanceAssessment(PerformanceAssessmentDto performanceAssessmentDto);

    void updatePaContent(List<PaContent> paContents);

    List<SpaAssessmentPeople> getSpaAssessmentPeoples(String id);

    void updateSpaAssessmentPeople(PerformanceAssessmentDto performanceAssessmentDto);

    void clearPaContent(String id);

    List<String> getPerformanceAssessmentDetNum(String id);
}
