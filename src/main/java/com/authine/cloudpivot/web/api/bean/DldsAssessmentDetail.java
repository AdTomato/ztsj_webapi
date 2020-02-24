package com.authine.cloudpivot.web.api.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: wangyong
 * @Date: 2020-02-18 13:46
 * @Description: 副职及以上领导人员测评数据设置详情
 */
@Data
public class DldsAssessmentDetail implements Serializable {

    /**
     * id
     */
    private String id;

    /**
     * 父id
     */
    private String parentId;

    /**
     * 排序字段
     */
    private Double sortKey;

    /**
     * 姓名
     */
    private String userName;

    /**
     * 职级
     */
    private String position;

    /**
     * 优秀
     */
    private Double excellentPoint;

    /**
     * 称职
     */
    private Double competentPoint;

    /**
     * 基本称职
     */
    private Double basicCompetencePoint;

    /**
     * 不称职
     */
    private Double incompetentPoint;

}
