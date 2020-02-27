package com.authine.cloudpivot.web.api.parameter;

import com.authine.cloudpivot.web.api.bean.PaContent;
import lombok.Data;

import java.util.List;

/**
 * @Author: wangyong
 * @Date: 2020-02-26 09:38
 * @Description: 履职考核插入考核明细接口的参数
 */
@Data
public class InsertPerformanceAssessmentDetail {

    private String id;
    private List<PaContent> paContents;

}
