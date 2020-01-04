package com.authine.cloudpivot.web.api.bean;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @Author: wangyong
 * @Date: 2020-01-05 01:50
 * @Description: 选人用人工作民主评议表
 */
@Data
public class DemocraticEvaluation extends BaseBean{

    /**
     * 单位
     */
    private String unit;

    /**
     * 日期
     */
    private Date date;

    /**
     * 1、对本企业（本单位）选人用人工作的总体评价
     */
    private String generalEvaluation;

    /**
     * 2、对本企业（本单位）执行选人用人工作政策法规情况的看法
     */
    private String regulationLawsOpinion;

    /**
     * 3、对本企业（本单位）整治用人上不正之风工作的看法
     */
    private String badPractiveOpinion;

    /**
     * 4、对本企业（本单位）深化干部人事制度改革的看法
     */
    private String institutionalReformOpinion;

    /**
     * 5、您认为目前本企业（本单位）选人用人工作存在的突出问题是什么（可多选）
     */
    private String prominentProblem;

    /**
     * 具体内容
     */
    private String concreteContent;

    /**
     * 意见和建议
     */
    private String commentSuggestion;

    /**
     * 当前ip
     */
    private String now_ip;

    /**
     * 查重
     */
    private String check;

}
