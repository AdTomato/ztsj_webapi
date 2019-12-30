package com.authine.cloudpivot.web.api.bean;

import lombok.Data;

import java.util.Date;

/**
 * @Author: wangyong
 * @Date: 2019-12-26 14:11
 * @Description: 领导人员考核结果
 */
@Data
public class AssessmentResult extends BaseBean {

    private String id;
    private String leadershipPerson;
    private String assessContent;
    private Date assessTime;
    private String assessResult;
    private String pId;
    private String time;

}
