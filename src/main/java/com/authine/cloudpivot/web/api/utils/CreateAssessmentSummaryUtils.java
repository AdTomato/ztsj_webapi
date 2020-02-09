package com.authine.cloudpivot.web.api.utils;

import com.authine.cloudpivot.engine.api.facade.BizObjectFacade;
import com.authine.cloudpivot.engine.api.model.runtime.BizObjectModel;
import com.authine.cloudpivot.web.api.controller.base.BaseController;

import java.util.HashMap;
import java.util.Map;

public class CreateAssessmentSummaryUtils extends BaseController {

    /**
     * 创建年度考核得分汇总表
     *
     * @param annual 年度
     * @param userId 用户
     * @return 汇总表id
     */
    public String insertAssessmentDetail(String annual, String userId) {
        BizObjectFacade facade = this.getBizObjectFacade();
        BizObjectModel model = new BizObjectModel();
        model.setSchemaCode("assessment_summary");
        Map<String, Object> map = new HashMap<>();
        map.put("annual", annual);
        model.put(map);
        model.setSequenceStatus("COMPLETED");
        return facade.saveBizObjectModel(userId, model, "id");
    }

}
