package com.authine.cloudpivot.web.api.bean;

import lombok.Data;

/**
 * @Author:lfh
 * @Date:2020/2/25 15:47
 * @Description: 紧缺人才申报表
 */
@Data
public class AccurateTalentInfo {
    private String id;

    /**
     * 姓名
     */
    private String cultivateName;

    /**
     * 工作单位
     */
    private String workUnit;

    /**
     * 人才类别
     */
    private String talentCategory;
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
    private String nativePlace;

    /**
     * 出生年月
     */
    private String dateOfBirth;

    /**
     * 专业职称
     */
    private String professionalTechnicalTitle;
    /**
     * 职务
     */
    private String duty;
}
