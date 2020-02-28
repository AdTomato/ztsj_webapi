package com.authine.cloudpivot.web.api.service;

import com.authine.cloudpivot.web.api.bean.QualitativeAssess;
import com.authine.cloudpivot.web.api.bean.QualitativeAssessContent;

import java.util.List;

/**
 * @Author: wangyong
 * @Date: 2020-01-09 23:49
 * @Description: 定性考核接口类
 */
public interface IQualitativeAssess {

    void insertQualitativeAssessContent(List<QualitativeAssessContent> qualitativeAssessContentList);

    List<QualitativeAssessContent> getQualitativeAssessContentById(String id);

    Double getQualitativeAssessContentFinalScore(String id);

    void cleanQualitativeAssessContent(String id);

    void updateQualitativeAssessContentFinalScore(List<QualitativeAssessContent> qualitativeAssessContentList);

    String getAssessmentContent(String id);

    QualitativeAssess getqualitativeAssessById(String id);

    String getQualitativeAssessStatus(String id);

    List<String> getQualitativeAssessDetails(String id);
}
