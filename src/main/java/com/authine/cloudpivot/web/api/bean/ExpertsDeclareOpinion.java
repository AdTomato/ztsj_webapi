package com.authine.cloudpivot.web.api.bean;

import lombok.Data;

import java.util.List;

/**
 * @Author:lfh
 * @Date: 2020/1/13 16:51
 * @Description：
 */
@Data
public class ExpertsDeclareOpinion {

    /**
     * id
     */
    private String id;

    /**
     * 年度
     */
    private String Oannual;

    /**
     * 评委
     */
    private List<User> OJudges;

    /**
     * 申报级别
     */
    private String OexpertsDeclareRank;

    /**
     * 通过人数
     */
    private Integer passPerson;

    /**
     * 通过票数
     */
    private Integer passPoll;

    /**
     * 申报系别
     */
    private String declareDept;

    /**
     * 专家申报意见
     */
    private List<ReviewAppointmentComments> reviewAppointmentCommentsList;
}
