package com.authine.cloudpivot.web.api.dto;

import com.authine.cloudpivot.web.api.bean.BaseBean;
import com.authine.cloudpivot.web.api.bean.PaContent;
import com.authine.cloudpivot.web.api.bean.PerformanceAssessment;
import lombok.Data;

import java.util.List;

/**
 * @Author: wangyong
 * @Date: 2020-02-26 10:01
 * @Description: 履职考核表dto
 */
@Data
public class PerformanceAssessmentDto extends PerformanceAssessment {

    List<PaContent> paContents;

}
