package com.authine.cloudpivot.web.api.parameter;

import com.authine.cloudpivot.web.api.bean.SpaAssessmentPeople;
import com.authine.cloudpivot.web.api.bean.SpaSurveyContent;
import com.authine.cloudpivot.web.api.bean.User;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @Author: wangyong
 * @Date: 2020-02-26 00:35
 * @Description: 发起履职考核接口的参数
 */
@Data
public class SendPerformanceAssessmentWf {

    private String id;
    private String assessmentContent;
    private String annual;
    private Date date;
    private User unit;
    private List<User> judges;
    private List<SpaSurveyContent> spaSurveyContents;
    private List<SpaAssessmentPeople> spaAssessmentPeoples;

}
