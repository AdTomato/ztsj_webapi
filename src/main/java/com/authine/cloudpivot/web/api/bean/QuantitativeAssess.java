package com.authine.cloudpivot.web.api.bean;


import lombok.Data;

/**
 * @Author: wangyong
 * @Date: 2020-01-09 00:46
 * @Description: 定量考核评分表
 */
@Data
public class QuantitativeAssess extends BaseBean {

    /**
     * 单位
     */
    private String unit;

    /**
     * 被考核人姓名
     */
    private String assessee;

    /**
     * 年度
     */
    private String annual;

    /**
     * 评委
     */
    private String judge;

    /**
     * 安全管理
     */
    private Double safeDeductionScore;

    /**
     * 质量管理
     */
    private Double qualityDeductionScore;

    /**
     * 进度及施工组织管理
     */
    private Double scDeductionScore;

    /**
     * 技术管理
     */
    private Double skillDeductionScore;

    /**
     * 工程分包管理
     */
    private Double enginDeductionScore;

    /**
     * 铁路、公路信用评价
     */
    private Double creditDeductionScore;

    /**
     * 沟通协调、重大问题报告
     */
    private Double problemDeductionScore;

    /**
     * 管控工作规范性
     */
    private Double controlDeductionScore;

    /**
     * 对片区内在建项目的服务、指导
     */
    private Double serviceDeductionScore;

    /**
     * 稽查纪律
     */
    private Double disciplineDeductionScore;

}
