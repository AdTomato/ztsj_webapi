package com.authine.cloudpivot.web.api.bean;

import lombok.Data;

import java.util.Date;

/**
 * @Author: wangyong
 * @Date: 2020-01-19 00:06
 * @Description: 系统的附件表
 */
@Data
public class HBizAttachment {

    private String id;
    /**
     * 关联业务对象id
     */
    private String bizObjectId;

    /**
     * 数据项编码
     */
    private String bizPropertyCode;

    /**
     * 创建时间
     */
    private Date createdTime;

    /**
     * 创建人
     */
    private String creater;

    /**
     * 文件扩展名
     */
    private String fileExtension;

    /**
     * 文件大小
     */
    private Integer fileSize;

    /**
     * 文件资源mime type
     */
    private String mimeType;

    /**
     * 文件名称
     */
    private String name;

    /**
     * 业务对象主表id
     */
    private String parentBizObjectId;

    /**
     * 业务对象主表模型编码
     */
    private String parentSchemaCode;

    /**
     * 资源存储id或url
     */
    private String refId;

    /**
     * 所属业务模型编码
     */
    private String schemaCode;

}
