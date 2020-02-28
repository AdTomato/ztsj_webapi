package com.authine.cloudpivot.web.api.mapper;

import com.authine.cloudpivot.web.api.bean.QualitativeAssess;
import com.authine.cloudpivot.web.api.bean.QualitativeAssessContent;
import jdk.internal.dynalink.linker.LinkerServices;

import java.util.List;

/**
 * @Author: wangyong
 * @Date: 2020-01-09 23:42
 * @Description: 定性考核mapper
 */
public interface QualitativeAssessMapper {

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
