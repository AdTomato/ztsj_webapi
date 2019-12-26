package com.authine.cloudpivot.web.api.bean;

import lombok.Data;
import org.codehaus.jackson.map.Serializers;

/**
 * @Author: wangyong
 * @Date: 2019-12-24 16:16
 * @Description: 职代会测评表
 */
@Data
public class StaffCongressEvaluation extends BaseBean {

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
