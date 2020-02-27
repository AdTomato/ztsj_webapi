package com.authine.cloudpivot.web.api.bean;

import lombok.Data;

import java.util.Date;

/**
 * @Author:lfh
 * @Date:2020/2/25 16:10
 * @Description:
 */
@Data
public class AtStudyExperience {

    /**
     * id
     */
    private String id;

    /**
     * 字表业务对象父类id
     */
    private String parentId;

    /**
     * 毕业院校
     */
    private String graduateInstitutions;

    /**
     * 毕业时间
     */
    private Date timeOfGraduation;

    /**
     * 专业
     */
    private String major;

    /**
     * 排序字段
     */
    private String sortKey;
}
