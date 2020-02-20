package com.authine.cloudpivot.web.api.service;

import com.authine.cloudpivot.web.api.bean.DeputyLeadershipDateSet;
import com.authine.cloudpivot.web.api.bean.DldsAssessmentDetail;

import java.util.List;

/**
 * @Author: wangyong
 * @Date: 2020-02-18 15:16
 * @Description:
 */
public interface DeputyLeadershipService {

    public List<DldsAssessmentDetail> getAssessmentDetailByParentId(String parentId);

    public void updateAssessmentDetail(List<DldsAssessmentDetail> dldsAssessmentDetails);

    public DeputyLeadershipDateSet getDeputyLeadershipDateSet();

    public DeputyLeadershipDateSet getDeputyLeadershipDateSetById(String id);

    public Integer isCanSubmit(String ip);
}
