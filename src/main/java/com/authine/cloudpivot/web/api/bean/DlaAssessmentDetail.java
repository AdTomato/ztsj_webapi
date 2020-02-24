package com.authine.cloudpivot.web.api.bean;

import lombok.Data;

/**
 * @Author: wangyong
 * @Date: 2020-02-18 15:51
 * @Description: 副职及以上领导人员考核表明细
 */
@Data
public class DlaAssessmentDetail {

    private String userName;
    private String position;
    private String evaluationOpinion;
    private String pid;
    private String did;

}
