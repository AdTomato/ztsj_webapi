package com.authine.cloudpivot.web.api.bean;

import lombok.Data;

import java.util.Date;

/**
 * @Author:lfh
 * @Date: 2020/1/13 9:48
 * @Description： 专家申报表
 */

@Data
public class ExpertsDeclare  extends BaseBean{

    /**
     * 申报级别
     */
    private String expertsDeclareRank;

    /**
     * 赞成人数
     */
    private Integer agreePoll;

    /**
     *弃权人数
     */
    private Integer waiverPoll;

    /**
     * 反对人数
     */
    private Integer opposePoll;


    /**
     * 投票结果
     */
    private String pollStatus;

}
