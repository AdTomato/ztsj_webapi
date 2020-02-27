package com.authine.cloudpivot.web.api.bean;

import lombok.Data;

import java.util.Date;

/**
 * @Author:lfh
 * @Date:2020/2/25 15:56
 * @Description:
 */
@Data
public class AtWorkExperience {

    /**
     * id
     */
    private String id;

    /**
     * 字表业务对象父类id
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
     * 工作单位
     */
    private String workUnit;

    /**
     * 部门
     */
    private String department;
}
