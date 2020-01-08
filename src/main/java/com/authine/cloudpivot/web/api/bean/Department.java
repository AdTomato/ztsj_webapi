package com.authine.cloudpivot.web.api.bean;

import com.authine.cloudpivot.engine.enums.type.DeptType;
import lombok.Data;

/**
 * 数据库中存储部门的控件
 */
@Data
public class Department {
    /**
     * 部门id
     */
    private String id;
    /**
     * 部门类型
     */
    private Integer type;

}
