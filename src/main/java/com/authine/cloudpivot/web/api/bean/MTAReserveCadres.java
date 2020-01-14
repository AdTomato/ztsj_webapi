package com.authine.cloudpivot.web.api.bean;

import lombok.Data;

/**
 * @Author:lfh
 * @Date: 2020/1/1 11:28
 * @Description：班子考核表 公司后备干部民主推荐表
 */
@Data
public class MTAReserveCadres {

    /**
     * id值
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
     * 被推荐人职务
     */
    private String recommendPosition;

    /**
     * 被推荐人姓名
     */
    private String referralName;

    /**
     * 被推荐人职务
     */
    private String referralNowPosition;

    /**
     * 测评单位
     */
    private String pId;
}
