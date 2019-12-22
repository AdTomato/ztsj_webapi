package com.authine.cloudpivot.web.api.bean;

import lombok.Data;

/**
 * 数据库中存储人员的控件
 */
@Data
public class User {
    /**
     * 人员id
     */
    private String id;

    /**
     * 人员类型
     */
    private Integer type;
}
