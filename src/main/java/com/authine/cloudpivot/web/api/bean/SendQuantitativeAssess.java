package com.authine.cloudpivot.web.api.bean;

import lombok.Data;

import java.util.List;

/**
 * @Author: wangyong
 * @Date: 2020-01-08 10:59
 * @Description: 发起定量考核
 */
@Data
public class SendQuantitativeAssess {

    /**
     * 被考核人
     */
    public List<User> assessee;

    /**
     * 年度
     */
    public String annual;

    /**
     * 评委
     */
    public List<User> judge;

    /**
     * 部门对应评委
     */
    public List<SqaJudges> sqaJudges;


}
