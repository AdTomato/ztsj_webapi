package com.authine.cloudpivot.web.api.bean;

import lombok.Data;

import java.util.List;

/**
 * @Author: wangyong
 * @Date: 2020-01-17 15:50
 * @Description: 紧缺人才面试发起表
 */
@Data
public class SIntroduceEvaluation extends BaseBean {

    private String userName;
    private Integer age;
    private String nativePlace;
    private String nowUnitAndDuty;
    private String recommendCompany;
    private List<User> ordinaryJudges;
    private List<User> professionalJudges;

}
