package com.authine.cloudpivot.web.api.bean;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: wangyong
 * @Date: 2020-02-19 09:28
 * @Description: 副职及以上领导人员数据设置表
 */
@Data
public class DeputyLeadershipDateSet implements Serializable {

    private String id;
    private String name;
    private String assessmentTitle;
    private String assessmentDate;
    private List<DldsAssessmentDetail> dldsAssessmentDetails;

}
