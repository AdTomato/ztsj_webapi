package com.authine.cloudpivot.web.api.bean;

import lombok.Data;

/**
 * @Author: wangyong
 * @Date: 2019/12/15 16:30
 * @Description:
 */
@Data
public class DepartmentAssessment extends BaseBean {

    private String assessedDepartment;
    private String assessedContent;
    private String annualSummary;
    private String annual;
    private String judge;

}
