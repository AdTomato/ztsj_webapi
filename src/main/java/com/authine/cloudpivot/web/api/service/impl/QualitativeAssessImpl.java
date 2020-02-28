package com.authine.cloudpivot.web.api.service.impl;

import com.authine.cloudpivot.web.api.bean.QualitativeAssess;
import com.authine.cloudpivot.web.api.bean.QualitativeAssessContent;
import com.authine.cloudpivot.web.api.mapper.QualitativeAssessMapper;
import com.authine.cloudpivot.web.api.service.IQualitativeAssess;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: wangyong
 * @Date: 2020-01-09 23:49
 * @Description:
 */
@Service
public class QualitativeAssessImpl implements IQualitativeAssess {

    @Resource
    QualitativeAssessMapper qualitativeAssessMapper;

    @Override
    public void insertQualitativeAssessContent(List<QualitativeAssessContent> qualitativeAssessContentList) {
        qualitativeAssessMapper.insertQualitativeAssessContent(qualitativeAssessContentList);
    }

    @Override
    public List<QualitativeAssessContent> getQualitativeAssessContentById(String id) {
        return qualitativeAssessMapper.getQualitativeAssessContentById(id);
    }

    @Override
    public Double getQualitativeAssessContentFinalScore(String id) {
        return qualitativeAssessMapper.getQualitativeAssessContentFinalScore(id);
    }

    @Override
    public void cleanQualitativeAssessContent(String id) {
        qualitativeAssessMapper.cleanQualitativeAssessContent(id);
    }

    @Override
    public void updateQualitativeAssessContentFinalScore(List<QualitativeAssessContent> qualitativeAssessContentList) {
        qualitativeAssessMapper.updateQualitativeAssessContentFinalScore(qualitativeAssessContentList);
    }

    @Override
    public String getAssessmentContent(String id) {
        return qualitativeAssessMapper.getAssessmentContent(id);
    }

    @Override
    public QualitativeAssess getqualitativeAssessById(String id) {
        return qualitativeAssessMapper.getqualitativeAssessById(id);
    }

    @Override
    public String getQualitativeAssessStatus(String id) {
        return qualitativeAssessMapper.getQualitativeAssessStatus(id);
    }

    @Override
    @Transactional
    public List<String> getQualitativeAssessDetails(String id) {
        return qualitativeAssessMapper.getQualitativeAssessDetails(id);
    }
}
