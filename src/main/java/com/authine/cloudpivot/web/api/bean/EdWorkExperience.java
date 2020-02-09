package com.authine.cloudpivot.web.api.bean;

import lombok.Data;

import java.util.Date;

/**
 * @Author:lfh
 * @Date: 2020/1/13 11:00
 * @Description： 经历 专家申报表 工作经历子表
 */
@Data
public class EdWorkExperience {

    /**
     * 工作经历表id
     */
    private String id;

    /**
     * 子表业务对象父类ID
     */
    private String parentId;

    /**
     * 开始时间
     */
    private Date startTime;

    /**
     * 结束时间
     */
    private Date endTime;

    /**
     * 单位名称
     */
    private String nameOfOrganization;

    /**
     * 从事专业
     */
    private String majorIn;

    /**
     * 职务
     */
    private String duty;

    /**
     * 排序字段
     */
    private String sortKey;
}
