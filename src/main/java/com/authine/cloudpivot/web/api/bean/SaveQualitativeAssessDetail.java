package com.authine.cloudpivot.web.api.bean;

import lombok.Data;

import java.util.List;

/**
 * @Author: wangyong
 * @Date: 2020-01-13 09:20
 * @Description:
 */
@Data
public class SaveQualitativeAssessDetail {

    private String id;
    private List<QualitativeAssessDetail> qualitativeAssessDetails;

}
