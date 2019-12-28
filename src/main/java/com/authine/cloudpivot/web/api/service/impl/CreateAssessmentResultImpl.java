package com.authine.cloudpivot.web.api.service.impl;

import com.authine.cloudpivot.engine.api.facade.BizObjectFacade;
import com.authine.cloudpivot.engine.api.model.runtime.BizObjectModel;
import com.authine.cloudpivot.web.api.bean.AssessmentResult;
import com.authine.cloudpivot.web.api.service.ICreateAssessmentResult;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: wangyong
 * @Date: 2019-12-27 09:39
 * @Description:
 */
@Service
public class CreateAssessmentResultImpl implements ICreateAssessmentResult {
    @Override
    public List<String> createAssessmentResults(BizObjectFacade objectFacade, String userId, List<AssessmentResult> arList) {

        List<BizObjectModel> bizObjects = new ArrayList<>();

        for (AssessmentResult ar :
                arList) {
            BizObjectModel model = new BizObjectModel();
            model.setSchemaCode("AssessmentResult");
            model.setSequenceStatus("COMPLETED");
            Map data = new HashMap();
            data.put("leadershipPerson", ar.getLeadershipPerson());
            data.put("assess_content", ar.getAssessContent());
            data.put("assess_time", ar.getAssessTime());
            data.put("assess_result", ar.getAssessResult());
            model.put(data);
            bizObjects.add(model);
        }
        return objectFacade.addBizObjects(userId, bizObjects, "id");
    }
}
