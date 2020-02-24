package com.authine.cloudpivot.web.api.service;

import com.authine.cloudpivot.web.api.bean.DeputyLeadershipDateSet;
import com.authine.cloudpivot.web.api.bean.DlaAssessmentDetail;
import com.authine.cloudpivot.web.api.bean.DldsAssessmentDetail;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    void updateData(String parentId, List<DlaAssessmentDetail> dlaAssessmentDetails);

    public List<DlaAssessmentDetail> getAllDlaAssessmentDetail(String did);

    public String getNewDeputyLeadershipDateSetId();
}
