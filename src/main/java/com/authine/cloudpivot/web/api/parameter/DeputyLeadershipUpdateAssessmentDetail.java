package com.authine.cloudpivot.web.api.parameter;

import com.authine.cloudpivot.web.api.bean.DlaAssessmentDetail;
import lombok.Data;

import java.util.List;

/**
 * @Author: wangyong
 * @Date: 2020-02-19 10:34
 * @Description: 副职及以上更新接口参数
 */
@Data
public class DeputyLeadershipUpdateAssessmentDetail {

    private String parentId;
    List<DlaAssessmentDetail> dlaAssessmentDetails;

}
