package com.authine.cloudpivot.web.api.bean;

import com.authine.cloudpivot.web.api.utils.DoubleUtils;
import lombok.Data;

/**
 * @Author: wangyong
 * @Date: 2020-02-26 00:33
 * @Description: 发起履职考核里面的考核人员子表
 */
@Data
public class SpaAssessmentPeople {

    private String id;
    private String parentId;
    private Double sortKey;
    private String userName;
    private String position;
    private Double finalScore;
    private String result;

}
