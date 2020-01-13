package com.authine.cloudpivot.web.api.bean;

import lombok.Data;

import java.util.List;

/**
 * @Author: wangyong
 * @Date: 2020-01-09 23:17
 * @Description: 发起定性考核
 */
@Data
public class SendQualitativeAssess {

    private List<User> assessee;

    private String annual;

    private String assessmentContent;

    private List<User> judge;

    /**
     * @Author: wangyong
     * @Date: 2020-01-10 00:02
     * @Description:
     */
    public static class SendQualitativeAssessDetail {
    }
}
