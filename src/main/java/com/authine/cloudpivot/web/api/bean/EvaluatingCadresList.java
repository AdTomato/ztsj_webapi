package com.authine.cloudpivot.web.api.bean;

import lombok.Data;

import java.util.Date;

/**
 * @Author:lfh
 * @Date: 2020/1/7 10:38
 * @Description： 新选拔干部民评议表的评价干部表
 */
@Data
public class EvaluatingCadresList {
    /**
     * 民主测评表id
     */
    private String id;

    /**
     * 民主测评表父id
     */
    private String parentId;

    /**
     * 排序字段
     */
    private String sortKey;

    /**
     * 姓名
     */
    private String userName;

    /**
     * 性别
     */
    private String gender;

    /**
     * 出生年月
     */
    private Date dateOfBirth;

    /**
     * 原职务
     */
    private String rawDuty;

    /**
     * 现职务
     */
    private String cashDuty;

    /**
     * 对任用干部看法
     */
    private String perspective;

    /**
     * 是否存在拉票、跑官、要官行为
     */
    private String ifThereIsA;

    /**
     * 测评单位
     */
    private String pId;
}
