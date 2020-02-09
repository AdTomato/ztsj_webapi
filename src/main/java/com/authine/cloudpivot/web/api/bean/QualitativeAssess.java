package com.authine.cloudpivot.web.api.bean;

import lombok.Data;

/**
 * @Author: wangyong
 * @Date: 2020-01-10 16:57
 * @Description: 定性考核评分表
 */
@Data
public class QualitativeAssess extends BaseBean {

    private String assessee;
    private String assessmentContent;
    private String annual;
    private String judge;


}
