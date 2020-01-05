package com.authine.cloudpivot.web.api.bean;

import io.swagger.models.auth.In;
import lombok.Data;

import java.util.Date;

/**
 * @Author: wangyong
 * @Date: 2020-01-02 10:19
 * @Description: 发起工作民主评议表
 */
@Data
public class SDemocraticEvaluation extends BaseBean{

    /**
     * 单位
     */
    private String unit;

    /**
     * 日期
     */
    private Date date;

    /**
     * 满意票数
     */
    private Integer gSatisfiedPoll;

    /**
     * 基本满意票数
     */
    private Integer gBasicSatisfiedPoll;

    /**
     * 不满意票数
     */
    private Integer gNoSatisfiedPoll;

    /**
     * 不了解票数
     */
    private Integer gNoUnderstandPoll;

    /**
     * 满意票数
     */
    private Integer rSatisfiedPoll;

    /**
     * 基本满意票数
     */
    private Integer rBasicSatisfiedPoll;

    /**
     * 不满意票数
     */
    private Integer rNoSatisfiedPoll;

    /**
     * 不了解票数
     */
    private Integer rNoUnderstandPoll;

    /**
     * 满意票数
     */
    private Integer bSatisfiedPoll;

    /**
     * 基本满意票数
     */
    private Integer bBasicSatisfiedPoll;

    /**
     * 不满意票数
     */
    private Integer bNoSatisfiedPoll;

    /**
     * 不了解票数
     */
    private Integer bNoUnderstandPoll;

    /**
     * 满意票数
     */
    private Integer iSatisfiedPoll;

    /**
     * 基本满意票数
     */
    private Integer iBasicSatisfiedPoll;

    /**
     * 不满意票数
     */
    private Integer iNoSatisfiedPoll;

    /**
     * 不了解票数
     */
    private Integer iNoUnderstandPoll;

    /**
     * 执行《干部任用条例》规定的资格、条件和程序不严格
     */
    private Integer noStrictPoll;

    /**
     * 任人唯亲
     */
    private Integer appointPeoplePoll;

    /**
     * 领导干部用人上个人说了算
     */
    private Integer individualPoll;

    /**
     * 跑官要官，说情打招呼
     */
    private Integer runOfficePoll;

    /**
     * 买官卖官
     */
    private Integer buySellOfficePoll;

    /**
     * 拉票
     */
    private Integer canvassingPoll;

    /**
     * 不存在突出问题
     */
    private Integer noOutstandingProblemsPoll;

    /**
     * 其他问题
     */
    private String otherProblems;

    /**
     * 意见和建议
     */
    private String commentSuggestion;

    /**
     * 参会人数
     */
    private Integer participantsPeoples;

    /**
     * 投票人数
     */
    private Integer votePeoples;

    /**
     * 是否关闭投票
     */
    private String isOrNotCloseVote;


}
