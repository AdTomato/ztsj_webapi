package com.authine.cloudpivot.web.api.service.impl;

import com.authine.cloudpivot.web.api.bean.DeputyLeadershipDateSet;
import com.authine.cloudpivot.web.api.bean.DldsAssessmentDetail;
import com.authine.cloudpivot.web.api.mapper.DeputyLeadershipMapper;
import com.authine.cloudpivot.web.api.service.DeputyLeadershipService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: wangyong
 * @Date: 2020-02-18 15:48
 * @Description:
 */
@Service
public class DeputyLeadershipServiceImpl implements DeputyLeadershipService {

    @Resource
    DeputyLeadershipMapper deputyLeadershipMapper;


    @Override
    public List<DldsAssessmentDetail> getAssessmentDetailByParentId(String parentId) {
        return deputyLeadershipMapper.getAssessmentDetailByParentId(parentId);
    }

    @Override
    public void updateAssessmentDetail(List<DldsAssessmentDetail> dldsAssessmentDetails) {
        deputyLeadershipMapper.updateAssessmentDetail(dldsAssessmentDetails);
    }

    @Override
    public DeputyLeadershipDateSet getDeputyLeadershipDateSet() {
        return deputyLeadershipMapper.getDeputyLeadershipDateSet();
    }

    @Override
    public DeputyLeadershipDateSet getDeputyLeadershipDateSetById(String id) {
        return deputyLeadershipMapper.getDeputyLeadershipDateSetById(id);
    }

    @Override
    public Integer isCanSubmit(String ip) {
        return deputyLeadershipMapper.isCanSubmit(ip);
    }


}

