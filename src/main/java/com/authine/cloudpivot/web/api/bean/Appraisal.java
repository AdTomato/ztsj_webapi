package com.authine.cloudpivot.web.api.bean;

import lombok.Data;

/**
 * @Author: wangyong
 * @Date: 2019-12-22 10:42
 * @Description: 班子成员民主测评表
 */
@Data
public class Appraisal {

    /**
     * 班子成员民主测评表id
     */
    private String id;

    /**
     * 班子成员民主测评表parentId
     */
    private String parentId;

    /**
     * 领导人员
     */
    private String leadershipName;

    /**
     * 职称
     */
    private String leadershipDuty;

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
