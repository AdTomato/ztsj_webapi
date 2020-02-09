package com.authine.cloudpivot.web.api.bean;

import lombok.Data;

/**
 * @Author:lfh
 * @Date: 2020/1/7 10:33
 * @Description：EvaluatingCadres 新选拔干部民主评议表
 */
@Data
public class EvaluatingCadres extends BaseBean {
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
