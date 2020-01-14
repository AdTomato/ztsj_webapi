package com.authine.cloudpivot.web.api.bean;

import lombok.Data;

/**
 * @Author:lfh
 * @Date: 2020/1/1 9:48
 * @Description： 班子考核表
 */
@Data
public class MajorTeamAssessment extends BaseBean {

    /**
     * 测评单位
     */
    private String unit;

    /**
     * 参会人数
     */
    private Integer participantsPeoples;

    /**
     * 投票人数
     */
    private Integer votePeoples;

    /**
     * 是否结束投票
     */
    private String isOrNotCloseVote;
}
