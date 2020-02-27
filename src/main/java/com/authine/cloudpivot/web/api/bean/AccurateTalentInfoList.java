package com.authine.cloudpivot.web.api.bean;

import lombok.Data;

import java.util.Date;

/**
 * @Author:lfh
 * @Date:2020/2/2514:01
 * @Description: 紧缺人才重点培养对象信息表
 */
@Data
public class AccurateTalentInfoList {

    /**
     * id
     */
    private String id;

    /**
     * 所在部门单位
     */
    private String placeTheUnit;

    /**
     * 人才类别
     */

    private String talentClasses;

    /**
     * 姓名
     */
    private String userName;

    /**
     * 性别
     */
    private String gender;

    /**
     * 政治面貌
     */
    private String politicsStatus;

    /**
     * 籍贯
     */
    private String nativePace;

    /**
     * 出生年月
     */
    private Date dateOfBirth;

    /**
     * 参加工作时间
     */
    private Date workTime;

    /**
     * 职称
     */
    private String theTitleOfTechenicalPost;

    /**
     * 现任职务
     */
    private String presentOccupation;

    /**
     * 入职后职位变动情况
     */
    private String changeOfPosition;

    /**
     * 导师姓名及职务
     */
    private String nameAndTitleOfTutor;

    /**
     *集中培训情况
     */
    private String intensiveTraining;

    /**
     * 年度考核情况
     */
    private String annualAssessment;

    /**
     * 培养期考核情况
     */
    private String assessmentCultivationPeriod;

    /**
     * 技术津贴标准
     */
    private String technicalAllowanceStandard;

    /**
     * 备注
     */
    private String comment;

}
