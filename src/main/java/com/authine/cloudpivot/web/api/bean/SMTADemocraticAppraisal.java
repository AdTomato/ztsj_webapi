package com.authine.cloudpivot.web.api.bean;

import lombok.Data;

/**
 * @Author:lfh
 * @Date: 2020/1/1 10:09
 * @Description：发起班子考核测评领导人员及非领导职务人员民主测评表
 */
@Data
public class SMTADemocraticAppraisal {

    /**
     * 民主测评表id
     */
    private String id;

    /**
     * 民主测评表父id
     */
    private String parentId;

    /**
     * 领导人员
     */
    private String leadershipName;

    /**
     * 领导人员职务
     */
    private String leadershipDuty;

    /**
     * 排序字段
     */
    private String sortKey;

    /**
     * 优秀票数
     */
    private Integer excellentPoll;

    /**
     * 称职票数
     */
    private Integer competentPoll;

    /**
     * 基本称职票数
     */
    private Integer basicCompetentPoll;

    /**
     * 不称职票数
     */
    private Integer notCompetentPoll;

}
