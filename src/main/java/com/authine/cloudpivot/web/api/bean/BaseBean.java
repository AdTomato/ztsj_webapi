package com.authine.cloudpivot.web.api.bean;

import lombok.Data;

import java.util.Date;
import java.util.UUID;

/**
 * 所有自建表格的基类
 */
@Data
public class BaseBean {

    /**
     * Id值
     */
    private String id;

    /**
     * 数据标题
     */
    private String name;

    /**
     * 创建人
     */
    private String creater;

    /**
     * 创建人部门
     */
    private String createdDeptId;

    /**
     * 所有人
     */
    private String owner;

    /**
     * 所有人部门
     */
    private String ownerDeptId;

    /**
     * 创建时间
     */
    private Date createdTime;

    /**
     * 修改人
     */
    private String modifier;

    /**
     * 修改时间
     */
    private Date modifiedTime;

    /**
     * 流程实例ID
     */
    private String workflowInstanceId;

    /**
     * 单据号
     */
    private String sequenceNo;

    /**
     * 单据状态
     */
    private String sequenceStatus;

    /**
     * 部门查询编码
     */
    private String ownerDeptQueryCode;

}
