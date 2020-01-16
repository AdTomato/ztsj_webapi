package com.authine.cloudpivot.web.api.bean;

import lombok.Data;

/**
 * @Author:lfh
 * @Date: 2020/1/13 23:43
 * @Description：
 */

@Data
public class ExpertsSelectResult {

    /**
     *
     */
    private String id;

    /**
     * 姓名
     */
    private String name;

    /**
     * 申报专业
     */
    private String expertsDeclareMajor;
}
