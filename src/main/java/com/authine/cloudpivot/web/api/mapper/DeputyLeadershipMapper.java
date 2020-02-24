package com.authine.cloudpivot.web.api.mapper;

import com.authine.cloudpivot.web.api.bean.DeputyLeadershipDateSet;
import com.authine.cloudpivot.web.api.bean.DlaAssessmentDetail;
import com.authine.cloudpivot.web.api.bean.DldsAssessmentDetail;

import java.util.List;
import java.util.Map;

/**
 * @Author: wangyong
 * @Date: 2020-02-18 14:11
 * @Description: 副职及以上领导人员测评表mapper
 */
public interface DeputyLeadershipMapper {

    public List<DldsAssessmentDetail> getAssessmentDetailByParentId(String parentId);

    public void updateAssessmentDetail(List<DldsAssessmentDetail> dldsAssessmentDetails);

    public DeputyLeadershipDateSet getDeputyLeadershipDateSet();

    public DeputyLeadershipDateSet getDeputyLeadershipDateSetById(String id);

    public Integer isCanSubmit(String ip);

    public List<DlaAssessmentDetail> getAllDlaAssessmentDetail(String did);

    public String getNewDeputyLeadershipDateSetId();

}
