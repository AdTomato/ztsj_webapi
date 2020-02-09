package com.authine.cloudpivot.web.api.bean;

import lombok.Data;

import java.util.Date;

/**
 * @Author:lfh
 * @Date: 2020/1/7 10:36
 * @Description： 发起新选拔干部民评议表的评价干部表
 */
@Data
public class SEvaluatingCadresList {
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
     * 满意票数(对任用该干部看法)
     */
    private Integer satisfiedPoll;

    /**
     * 基本满意票数(对任用该干部看法)
     */
    private Integer basicSatisfiedPoll;

    /**
     * 不满意票数(对任用该干部看法)
     */
    private Integer noSatisfiedPoll;

    /**
     * 不了解票数(对任用该干部看法)
     */
    private Integer noUnderstandPoll;
    /**
     * 存在票数(是否存在拉票、跑官、要官行为)
     */
    private Integer existencePoll;
    /**
     * 不存在票数(是否存在拉票、跑官、要官行为)
     */
    private Integer noExistencePoll;
    /**
     * 不了解票数(是否存在拉票、跑官、要官行为)
     */
    private Integer iNoUnderstandPoll;
}
