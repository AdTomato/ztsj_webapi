package com.authine.cloudpivot.web.api.bean;


import lombok.Data;

import java.util.List;

/**
 * 机关部门考核表
 */
@Data
public class LaunchAssessment extends BaseBean {

    /**
     * 被考核部门
     */
    private List<Department> assessedDepartment;

    /**
     * 评委
     */
    private List<User> judge;

    /**
     * 考核主体
     */
    private String assessedContent;

    /**
     * 年度
     */
    private String annual;

}
